package org.example.cabin.bms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.model.vo.ArticleVO;
import org.example.cabin.bms.service.BlogsContentService;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.result.PageResult;
import org.example.common.core.result.Result;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/v1/content")
public class ContentManagementController {

    @Autowired
    private BlogsContentService contentService;

    @GetMapping("/article/page")
    public PageResult<ArticleVO> getArticlesPage(ContentQuery query) {
        query.setUserId(SecurityUtils.getUserId());
        IPage<Article> bos = contentService.getArticlesPaginateByUser(query);
        IPage<ArticleVO> vos = new Page<>();
        for (Article article : bos.getRecords()){
            ArticleVO vo = new ArticleVO();
            vo.setContentAddress(article.getContentAddress());
            vo.setCoverAddress(article.getCoverAddress());
            vo.setHits(article.getHits());
            vo.setOriginalLink(article.getOriginalLink());
            vo.setStatus(article.getStatus());
            vo.setSubtitle(article.getSubtitle());
            vo.setSummary(article.getSummary());
            vo.setTitle(article.getTitle());
            vo.setType(article.getType());
            vo.setUserId(article.getUserId());
            vo.setVisibleRange(article.getVisibleRange());

            vos.getRecords().add(vo);
        }
        return PageResult.success(vos);
    }

//    @Operation(summary= "新增文章")
    @PutMapping("/article/save")
    public Result<Integer> saveArticle(ArticleForm form) {
        int i = contentService.addArticle(form);
        if (i == 0) {
            return Result.failed("新增文章失败");
        }else {
            return Result.success("新增文章成功", i);
        }
    }

}