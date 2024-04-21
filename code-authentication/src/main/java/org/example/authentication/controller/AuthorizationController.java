package org.example.authentication.controller;

import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.authentication.model.MemberUserDetails;
import org.example.authentication.service.MemberUserDetailsService;
import org.example.common.core.constant.JwtClaimConstants;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
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
public class AuthorizationController {

    @Autowired
    private MemberUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public Result<String> login(@RequestBody MemberUserDetails loginUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)){
            log.error("拒绝访问：用户名或密码错误");
            throw new BadCredentialsException("拒绝访问，用户名或密码错误");
        }

        // 如果认证通过，使用userid生成一个jwt，jwt存入BaseResultVO返回
        MemberUserDetails principal = (MemberUserDetails)authenticate.getPrincipal();
//        MemberUserDetails principal = (MemberUserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Map<String,Object> payload = new HashMap<>();
        payload.put("userId",principal.getId());
        payload.put("userName",principal.getUsername());
        String token = JWTUtil.createToken(payload, JwtClaimConstants.JWT_KEY.getBytes());

        return Result.success(token);
    }

}