package org.example.cabin.bms.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.form.ArticleForm;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.model.vo.ArticleVO;
import org.example.cabin.bms.service.BlogsArticleService;
import org.example.common.core.result.PageResult;
import org.example.common.core.result.Result;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/v1/content")
public class ContentManagementController {

    @Autowired
    private BlogsArticleService articleService;

    @GetMapping("/article/page")
    public PageResult<ArticleVO> getArticlesPage(ContentQuery query) {
        query.setUserId(SecurityUtils.getUserId());
        IPage<Article> bos = articleService.getArticlesPaginateByUser(query);
        IPage<ArticleVO> vos = new Page<>();
        for (Article article : bos.getRecords()){
            ArticleVO vo = new ArticleVO();
            BeanUtil.copyProperties(article, vo);
            vos.getRecords().add(vo);
        }
        return PageResult.success(vos);
    }

//    @Operation(summary= "新增文章")
    @PostMapping("/article/save")
    public Result<Integer> saveArticle(@RequestBody ArticleForm form) {
        int i = articleService.addArticle(form);
        if (i == 0) {
            return Result.failed("新增文章失败");
        }else {
            return Result.success("新增文章成功", i);
        }
    }

    @PutMapping("/article/updateById")
    public Result<Integer> updateArticleById(@RequestBody ArticleForm form){
        var i = articleService.updateArticleById(form);
        if (i == 0) {
            return Result.failed("更新文章失败");
        }else {
            return Result.success("更新文章失败", i);
        }
    }

}