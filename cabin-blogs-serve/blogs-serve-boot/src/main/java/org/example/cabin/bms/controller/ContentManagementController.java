package org.example.cabin.bms.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
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
        IPage<ArticleVO> vos = bos.convert(article -> {
            ArticleVO vo = new ArticleVO();
            BeanUtil.copyProperties(article, vo);
            return vo;
        });
        return PageResult.success(vos);
    }

//    @Operation(summary= "新增文章")
    @PostMapping("/article/save")
    public Result<Long> saveArticle(@RequestBody ArticleForm form) {
        if (ObjectUtil.isNull(form.getId())){
            Long id = articleService.addArticle(form);
            return Result.success("保存草稿成功", id);
        }else {
            articleService.updateArticleById(form);
            return Result.success("保存草稿成功", form.getId());
        }
    }

    @PutMapping("/article/updateById")
    public Result<Integer> updateArticleById(@RequestBody ArticleForm form){
        var i = articleService.updateArticleById(form);
        if (i == 0) {
            return Result.failed("更新文章失败");
        }else {
            return Result.success("更新文章成功", i);
        }
    }

    @GetMapping("/article/{id}")
    public Result<ArticleVO> getArticleById(@PathVariable("id") Long id){
        Article article = articleService.getArticleById(id);
        ArticleVO articleVO = new ArticleVO();
        BeanUtil.copyProperties(article, articleVO);
        return Result.success("获取文章成功", articleVO);
    }

    @DeleteMapping("/article/delete/{id}")
    public Result<Integer> deleteArticle(@PathVariable("id") Long id) {
        int i = articleService.deleteArticleById(id);
        if (i == 0) {
            return Result.failed("文章已被删除，无需再删");
        }else {
            return Result.success("删除文章成功", i);
        }
    }
}