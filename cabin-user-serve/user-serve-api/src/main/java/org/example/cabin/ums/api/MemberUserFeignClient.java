package org.example.cabin.ums.api;


import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 会员Feign客户端
 *
 * @author ComeTomorrow
 * @since 2024/4/9
 */
@FeignClient(name = "cabin-user-serve", contextId = "member")
public interface MemberUserFeignClient {

    /**
     * 根据openId获取会员认证信息
     *
     * @param openid
     * @return
     */
//    @GetMapping("/app-api/v1/members/openid/{openid}")
//    Result<MemberAuthDTO> loadUserByOpenId(@PathVariable String openid);

    /**
     * 根据手机号获取会员认证信息
     *
     * @param mobile 手机号
     * @return 会员认证信息
     */
    @GetMapping("/app-api/v1/members/mobile/{mobile}")
    Result<MemberAuthDTO> loadUserByMobile(@PathVariable String mobile);

}


