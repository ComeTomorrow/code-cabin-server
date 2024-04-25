package org.example.cabin.bms.controller;

import org.example.cabin.bms.service.BlogsContentService;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/v1/content")
public class ContentManagementController {

    @Autowired
    private BlogsContentService contentService;

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
