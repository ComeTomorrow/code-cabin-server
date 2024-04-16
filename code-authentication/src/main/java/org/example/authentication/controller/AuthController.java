package org.example.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.authentication.service.MemberUserDetailsService;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

/**
 * 认证控制器
 * <p>
 * 获取验证码、退出登录等接口
 * 注：登录接口不在此控制器，在过滤器OAuth2TokenEndpointFilter拦截端点(/oauth2/token)处理
 *
 * @author ComeTomorrow
 * @since 2024/4/10
 */
@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    @Autowired
    private MemberUserDetailsService userDetailsService;
    
//    @Autowired
//    private AuthenticationManager authenticationManager;

    @GetMapping("/captcha/{mobile}")
    public Result<UserDetails> login(@PathVariable("mobile") String mobile) {
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(mobile,"");
//        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        UserDetails userDetails = userDetailsService.loadUserByMobile(mobile);
        return Result.success(userDetails);
    }

}
