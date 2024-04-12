package org.example.authentication.controller;

import org.example.authentication.service.MemberUserDetailsService;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 * <p>
 * 获取验证码、退出登录等接口
 * 注：登录接口不在此控制器，在过滤器OAuth2TokenEndpointFilter拦截端点(/oauth2/token)处理
 *
 * @author haoxr
 * @since 3.1.0
 */
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private MemberUserDetailsService userDetailsService;

    @GetMapping("/captcha/{mobile}")
    public Result<UserDetails> loadUserByMobile(@PathVariable("mobile") String mobile) {
        UserDetails userDetails = userDetailsService.loadUserByMobile(mobile);
        return Result.success(userDetails);
    }

}
