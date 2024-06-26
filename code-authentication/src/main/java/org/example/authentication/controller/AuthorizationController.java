package org.example.authentication.controller;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.authentication.model.MemberUserDetails;
import org.example.authentication.model.RegisterDetails;
import org.example.authentication.service.MemberUserDetailsService;
import org.example.cabin.ums.dto.MemberAuthDTO;
import org.example.common.core.constant.JwtClaimConstants;
import org.example.common.core.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public Result<String> login(@RequestBody MemberUserDetails loginUser) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(),loginUser.getPassword());
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        // 如果认证通过，使用userid生成一个jwt，jwt存入BaseResultVO返回
        MemberUserDetails principal = (MemberUserDetails)authenticate.getPrincipal();

        // 生成jwt
        Map<String,Object> payload = new HashMap<>();
        payload.put(JwtClaimConstants.USER_ID, principal.getId());
        payload.put(JwtClaimConstants.MOBILE, principal.getUsername());
        payload.put(JwtClaimConstants.NICK_NAME, principal.getNickName());
        payload.put(JwtClaimConstants.ENABLED, principal.isEnabled());
        payload.put(JwtClaimConstants.ACCOUNT_NON_EXPIRED, principal.isAccountNonExpired());
        payload.put(JwtClaimConstants.ACCOUNT_NON_LOCKED, principal.isAccountNonLocked());
        payload.put(JwtClaimConstants.CREDENTIALS_NON_EXPIRED, principal.isCredentialsNonExpired());

        ZoneId zoneId = ZoneId.systemDefault();

        // 获取当前时间
        LocalDateTime nowTime = LocalDateTime.now();
        ZonedDateTime zonedNow = nowTime.atZone(zoneId);
        Date nowDate = Date.from(zonedNow.toInstant());

        // 获取60分钟之后
        LocalDateTime expireTime = nowTime.plus(60 * 60, ChronoUnit.SECONDS);
        ZonedDateTime zonedExpire = expireTime.atZone(zoneId);
        Date expireDate = Date.from(zonedExpire.toInstant());

        String token = JWT.create()
                .addPayloads(payload)
                .setIssuedAt(nowDate)
                .setNotBefore(nowDate)
                .setExpiresAt(expireDate)
                .setKey(JwtClaimConstants.JWT_KEY.getBytes())
                .sign();

        return Result.success(token);
    }

    @PutMapping("/register")
    public Result<Integer> register(@RequestBody RegisterDetails registerDetails) {
        MemberAuthDTO user = new MemberAuthDTO();
        user.setUsername(registerDetails.getUsername());
        String encode = passwordEncoder.encode(registerDetails.getPassword());
        user.setPassword(encode);
        user.setEnabled(registerDetails.getEnabled());

        Integer i = userDetailsService.createUser(user);
        if (i==null) {
            return Result.failed("注册失败，该手机号已被注册");
        }else {
            return Result.success("注册成功",null);
        }
    }
}