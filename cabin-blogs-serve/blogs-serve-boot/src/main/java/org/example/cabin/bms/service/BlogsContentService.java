package org.example.cabin.bms.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.model.vo.ArticleVO;

import java.util.List;

public interface BlogsContentService {

    /**
     * 根据用户id获得文章列表，并且将其分页
     * @param contentQuery
     * @return
     */
    IPage<Article> getArticlesPaginateByUser(ContentQuery contentQuery);
}
