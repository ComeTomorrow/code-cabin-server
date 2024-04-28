package org.example.cabin.bms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.cabin.bms.mapper.ArticleMapper;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.service.BlogsContentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlogsContentServiceImpl implements BlogsContentService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<Article> getArticlesPaginateByUser(ContentQuery query) {
        IPage page = query.startPage();
        IPage<Article> articles = articleMapper.selectPage(page, Wrappers.lambdaQuery(Article.class)
                .eq(Article::getUserId, query.getUserId()));
//                .select(Article::getId, Article::getId));
        return articles;
    }

    @Override
    public int addArticle(ArticleForm form){
        Article article = new Article();
        BeanUtil.copyProperties(form, article);
        return articleMapper.insert(article);
    }
}
