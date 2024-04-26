package org.example.cabin.bms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.cabin.bms.model.entity.Article;
import org.example.cabin.bms.model.query.ContentQuery;
import org.example.cabin.bms.model.vo.ArticleVO;
import org.example.cabin.bms.service.BlogsContentService;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.result.PageResult;
import org.example.common.core.result.Result;
import org.example.common.security.util.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/v1/content")
public class ContentManagementController {

    @Autowired
    private BlogsContentService contentService;

    @GetMapping("/article/page")
    public PageResult<ArticleVO> getArticlesPage(ContentQuery query) {
        System.out.println("username"+SecurityUtils.getUsername());
        System.out.println("userId"+SecurityUtils.getUserId());
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

//    @Operation(summary= "新增会员")
    @PutMapping("/add")
    public Result<Integer> addMember(@RequestBody MemberAuthDTO memberAuth) {
//        int i = contentService.addMemberUser(memberAuth);
//        if (i==0) {
//            return Result.failed("新增会员用户失败");
//        }else {
//            return Result.success("新增会员用户成功", i);
//        }
        return null;
    }

}