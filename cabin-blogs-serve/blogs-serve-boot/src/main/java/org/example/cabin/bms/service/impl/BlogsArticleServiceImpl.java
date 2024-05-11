package org.example.cabin.bms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.example.cabin.bms.mapper.*;
import org.example.cabin.bms.model.entity.*;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.service.BlogsArticleService;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Objects;

@Service
public class BlogsArticleServiceImpl implements BlogsArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ColumnMapper columnMapper;

    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public IPage<Article> getArticlesPaginateByUser(ContentQuery query) {
        IPage page = query.startPage();
        IPage<Article> articles = articleMapper.selectPage(page, Wrappers.lambdaQuery(Article.class)
                .eq(Article::getUserId, query.getUserId()));
        return articles;
    }

    @Transactional
    @Override
    public Long addArticle(ArticleForm form){
        Assert.isNull(form.getId(),"添加失败，文章已存在，禁止重复操作");

        // 获取登录用户id
        Long userId = SecurityUtils.getUserId();
        Assert.isTrue(Objects.isNull(form.getUserId())||form.getUserId().equals(userId),"用户异常");

        Article article = new Article();
        BeanUtil.copyProperties(form, article);
        article.setUserId(userId);
        articleMapper.insert(article);

        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            // 判断标签是否存在
            List<Long> tagIds = form.getTagIds();
            // 批量插入文章与标签的关联关系数据
            if (CollectionUtil.isNotEmpty(tagIds)){
                List<ArticleTag> tags = articleTagMapper.selectBatchIds(tagIds);
                ArticleTagArticleMapper articleTagArticleMapper = sqlSession.getMapper(ArticleTagArticleMapper.class);
                for (ArticleTag tag : tags) {
                    ArticleTagArticle relation = new ArticleTagArticle()
                            .setArticleId(article.getId())
                            .setArticleTagId(tag.getId());
                    articleTagArticleMapper.insert(relation);
                }
            }

            // 判断专栏是否存在
            List<Long> columnIds = form.getColumnIds();
            // 批量插入文章与栏目的关联关系数据
            if (CollectionUtil.isNotEmpty(columnIds)){
                List<Column> columns = columnMapper.selectBatchIds(columnIds);
                ColumnArticleMapper columnArticleMapper = sqlSession.getMapper(ColumnArticleMapper.class);
                for (Column column : columns) {
                    ColumnArticle relation = new ColumnArticle()
                            .setArticleId(article.getId())
                            .setColumnId(column.getId());
                    columnArticleMapper.insert(relation);
                }
            }
            sqlSession.commit(); // 批量提交
        }
        return article.getId();
    }

    @Transactional
    @Override
    public int updateArticleById(ArticleForm form){
        Assert.notNull(form.getId(),"更新失败，文章不存在");

        Long userId = SecurityUtils.getUserId();
        Assert.isTrue(Objects.isNull(form.getUserId())||form.getUserId().equals(userId),"用户异常");

        Article article = new Article();
        BeanUtil.copyProperties(form, article);
        int i = articleMapper.updateById(article);

        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            // 判断标签是否存在
            List<Long> tagIds = form.getTagIds();
            if (CollectionUtil.isNotEmpty(tagIds)){
                List<ArticleTag> tags = articleTagMapper.selectBatchIds(tagIds);
                // 批量插入文章与标签的关联关系数据
                if (CollectionUtil.isNotEmpty(tags)){
                    ArticleTagArticleMapper articleTagArticleMapper = sqlSession.getMapper(ArticleTagArticleMapper.class);
                    List<ArticleTagArticle> relations = articleTagArticleMapper.selectList(
                            Wrappers.lambdaQuery(ArticleTagArticle.class).eq(ArticleTagArticle::getArticleId,article.getId())
                    );
                    for (ArticleTagArticle relation : relations){
                        articleTagArticleMapper.deleteById(relation);
                    }
                    for (ArticleTag tag : tags) {
                        ArticleTagArticle relation = new ArticleTagArticle()
                                .setArticleId(article.getId())
                                .setArticleTagId(tag.getId());
                        articleTagArticleMapper.insert(relation);
                    }
                }
            }

            // 判断专栏是否存在
            List<Long> columnIds = form.getColumnIds();
            if (CollectionUtil.isNotEmpty(columnIds)){
                List<Column> columns = columnMapper.selectBatchIds(columnIds);
                // 批量插入文章与栏目的关联关系数据
                if (CollectionUtil.isNotEmpty(columns)){
                    ColumnArticleMapper columnArticleMapper = sqlSession.getMapper(ColumnArticleMapper.class);
                    List<ColumnArticle> relations = columnArticleMapper.selectList(
                            Wrappers.lambdaQuery(ColumnArticle.class).eq(ColumnArticle::getArticleId,article.getId())
                    );
                    for (ColumnArticle relation : relations){
                        columnArticleMapper.deleteById(relation);
                    }
                    for (Column column : columns) {
                        ColumnArticle relation = new ColumnArticle()
                                .setArticleId(article.getId())
                                .setColumnId(column.getId());
                        columnArticleMapper.insert(relation);
                    }
                }
            }
            sqlSession.commit(); // 批量提交
        }
        return i;
    }

    @Override
    public Article getArticleById(Long id) {
        return articleMapper.selectById(id);
    }
}
