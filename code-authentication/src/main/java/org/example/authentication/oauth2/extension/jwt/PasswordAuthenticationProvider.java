package org.example.authentication.oauth2.extension.jwt;

import lombok.extern.slf4j.Slf4j;
import org.example.authentication.service.MemberUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Objects;

@Slf4j
public class PasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private MemberUserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //从Authentication对象中获取用户名和身份凭证信息
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails loginUser = userDetailsService.loadUserByUsername(username);
        if (Objects.isNull(loginUser) || !passwordEncoder.matches(password, loginUser.getPassword())){
            //密码匹配失败抛出异常
            log.error("拒绝访问，用户名或密码错误");
            throw new BadCredentialsException("拒绝访问，用户名或密码错误");
        }
        log.info("访问成功："+loginUser);
        return new UsernamePasswordAuthenticationToken(loginUser, password, loginUser.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication == JwtAuthenticationToken.class || authentication == UsernamePasswordAuthenticationToken.class;
    }
}