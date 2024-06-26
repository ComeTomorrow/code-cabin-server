package org.example.cabin.ums.controller;

import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.cabin.ums.service.MemberUserService;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/app-api/v1/members")
public class MemberUserController {

    @Autowired
    private MemberUserService memberUserService;

//    @Operation(summary= "根据会员ID获取openid")
//    @GetMapping("/{memberId}/openid")
//    public Result<String> getMemberById(@PathVariable Long memberId) {
//
//        UmsMember member = umsMemberService.getOne(new LambdaQueryWrapper<UmsMember>()
//                .eq(UmsMember::getId, memberId)
//                .select(UmsMember::getOpenid));
//        String openid = member.getOpenid();
//        return Result.success(openid);
//    }

//    @Operation(summary= "新增会员")
    @PutMapping("/add")
    public Result<Integer> addMember(@RequestBody MemberAuthDTO memberAuth) {
        int i = memberUserService.addMemberUser(memberAuth);
        if (i == 0) {
            return Result.failed("新增会员用户失败");
        }else {
            return Result.success("新增会员用户成功", i);
        }
    }

//    @Operation(summary= "获取登录会员信息")
//    @GetMapping("/me")
//    public Result<MemberUserVO> getCurrMemberInfo() {
//        MemberUserVO memberVO = umsMemberService.getCurrMemberInfo();
//        return Result.success(memberVO);
//    }

//    @Operation(summary= "扣减会员余额")
//    @PutMapping("/{memberId}/balances/_deduct")
//    public Result deductBalance(@PathVariable Long memberId, @RequestParam Long amount) {
//        boolean result = umsMemberService.update(new LambdaUpdateWrapper<UmsMember>()
//                .setSql("balance = balance - " + amount)
//                .eq(UmsMember::getId, memberId));
//        return Result.judge(result);
//    }

//    @Operation(summary= "添加浏览历史")
//    @PostMapping("/view/history")
//    public <T> Result<T> addProductViewHistory(@RequestBody ProductHistoryVO product) {
//        Long memberId = SecurityUtils.getMemberId();
//        umsMemberService.addProductViewHistory(product, memberId);
//        return Result.success();
//    }

//    @Operation(summary= "获取浏览历史")
//    @GetMapping("/view/history")
//    public Result<Set<ProductHistoryVO>> getProductViewHistory() {
//        Long memberId = SecurityUtils.getMemberId();
//        Set<ProductHistoryVO> historyList = umsMemberService.getProductViewHistory(memberId);
//        return Result.success(historyList);
//
//    }

//    @Operation(summary= "根据 openid 获取会员认证信息")
//    @GetMapping("/openid/{openid}")
//    public Result<MemberAuthDTO> getMemberByOpenid(@PathVariable String openid) {
//        MemberAuthDTO memberAuthInfo = umsMemberService.getMemberByOpenid(openid);
//        return Result.success(memberAuthInfo);
//    }

//    @Operation(summary= "根据手机号获取会员认证信息",hidden = true)
    @GetMapping("/mobile/{mobile}")
    public Result<MemberAuthDTO> getMemberByMobile(@PathVariable("mobile") String mobile)
    {
        MemberAuthDTO memberAuthInfo = memberUserService.getMemberUserByMobile(mobile);
        return Result.success(memberAuthInfo);
    }

//    @Operation(summary ="获取会员地址列表")
//    @GetMapping("/{memberId}/addresses")
//    public Result<List<MemberAddressDTO>> listMemberAddress(
//            @Parameter(name = "会员ID") @PathVariable Long memberId
//    ) {
//        List<MemberAddressDTO> addresses = umsMemberService.listMemberAddress(memberId);
//        return Result.success(addresses);
//    }


}
