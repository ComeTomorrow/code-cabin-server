package org.example.cabin.bms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import org.example.cabin.bms.mapper.ArticleMapper;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.service.BlogsContentService;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Objects;

@Service
public class BlogsContentServiceImpl implements BlogsContentService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public IPage<Article> getArticlesPaginateByUser(ContentQuery query) {
        IPage page = query.startPage();
        IPage<Article> articles = articleMapper.selectPage(page, Wrappers.lambdaQuery(Article.class)
                .eq(Article::getUserId, query.getUserId()));
        return articles;
    }

    @Override
    public int addArticle(ArticleForm form){
        Long userId = SecurityUtils.getUserId();
        Article article = new Article();

        Assert.isTrue(Objects.isNull(form.getUserId())||form.getUserId().equals(userId),"”√ªß“Ï≥£");

        article.setUserId(userId);
        article.setContentAddress(form.getContentAddress());
        article.setCoverAddress(form.getCoverAddress());
        article.setHits(form.getHits());
        article.setOriginalLink(form.getOriginalLink());
        article.setStatus(form.getStatus());
        article.setSubtitle(form.getSubtitle());
        article.setSummary(form.getSummary());
        article.setTitle(form.getTitle());
        article.setType(form.getType());
        article.setVisibleRange(form.getVisibleRange());

        return articleMapper.insert(article);
    }

    @Override
    public int updateArticleById(ArticleForm form){
        Long userId = SecurityUtils.getUserId();

        Article article = new Article();
        article.setId(form.getId());
        article.setUserId(userId);
        article.setContentAddress(form.getContentAddress());
        article.setCoverAddress(form.getCoverAddress());
        article.setHits(form.getHits());
        article.setOriginalLink(form.getOriginalLink());
        article.setStatus(form.getStatus());
        article.setSubtitle(form.getSubtitle());
        article.setSummary(form.getSummary());
        article.setTitle(form.getTitle());
        article.setType(form.getType());
        article.setVisibleRange(form.getVisibleRange());

        return articleMapper.updateById(article);
    }
}
