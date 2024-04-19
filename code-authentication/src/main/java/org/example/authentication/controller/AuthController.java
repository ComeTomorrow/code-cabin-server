package org.example.authentication.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.authentication.model.MemberUserDetails;
import org.example.authentication.service.MemberUserDetailsService;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private MemberUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public Result<UserDetails> login(@RequestBody MemberUserDetails loginUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            throw new RuntimeException("erreo");
        }
        UserDetails userDetails = userDetailsService.loadUserByMobile(loginUser.getUsername());
        return Result.success(userDetails);
    }

}
