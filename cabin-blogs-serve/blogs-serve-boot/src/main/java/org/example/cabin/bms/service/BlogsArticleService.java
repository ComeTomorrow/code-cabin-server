package org.example.cabin.bms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;

public interface BlogsArticleService {

    /**
     * 根据用户id获得文章列表，并且将其分页
     * @param contentQuery
     * @return
     */
    IPage<Article> getArticlesPaginateByUser(ContentQuery contentQuery);

    /**
     * 新增文章
     * @param articleForm
     * @return
     */
    Long addArticle(ArticleForm articleForm);

    /**
     * 更新文章
     * @param articleForm
     * @return
     */
    int updateArticleById(ArticleForm articleForm);

    /**
     * 更新文章
     * @param id
     * @return
     */
    Article getArticleById(Long id);
}
