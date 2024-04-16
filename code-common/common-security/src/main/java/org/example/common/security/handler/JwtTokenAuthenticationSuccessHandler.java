package org.example.common.security.handler;

import cn.hutool.jwt.JWTUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.core.constant.JwtClaimConstants;
import org.example.common.core.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);  // 返回JSON
        response.setStatus(HttpStatus.OK.value());  // 状态码200
        // JWT信息
        Map<String, Object> jwtMap = new HashMap<>();
        jwtMap.put("username", authentication.getName());   // 用户名作为用户唯一标识，实际开发可以使用 用户ID
        jwtMap.put("expire_time", System.currentTimeMillis()+10000);  //
        // 创建令牌（hutool工具）
        String token = JWTUtil.createToken(jwtMap, JwtClaimConstants.JWT_KEY.getBytes());
        ObjectMapper mapper = new ObjectMapper();
        // 响应数据
        mapper.writeValue(response.getOutputStream(), Result.success(token));
    }
}
