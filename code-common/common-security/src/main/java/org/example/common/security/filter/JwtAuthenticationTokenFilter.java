package org.example.common.security.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.core.constant.JwtClaimConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        // 这里如果没有jwt，继续往后走，因为后面还有鉴权管理器等去判断是否拥有身份凭证，所以是可以放行的
        // 没有jwt相当于匿名访问，若有一些接口是需要权限的，则不能访问这些接口
        if (StrUtil.isBlankOrUndefined(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        JWT jwt = JWTUtil.parseToken(token);
        JWTPayload payload = jwt.getPayload();
        System.out.println(payload);

        Object username = payload.getClaim(JwtClaimConstants.MOBILE);


//        if (payload == null) {
//            throw new JwtException("token 异常");
//        }
//        payload.getClaim("");
//        if (jwtUtils.isTokenExpired(claim)) {
//            throw new JwtException("token 已过期");
//        }
////
//        String username = claim.getSubject();
//        // 获取用户的权限等信息
//
//        SysUser sysUser = sysUserService.getByUsername(username);
        var context = SecurityContextHolder.getContext();

        System.out.println(context);
        System.out.println( context.getAuthentication());

        // 构建UsernamePasswordAuthenticationToken,这里密码为null，是因为提供了正确的JWT,实现自动登录
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, "123456");
        authenticationToken.setAuthenticated(false);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println( context.getAuthentication());

        filterChain.doFilter(request, response);
    }
}
