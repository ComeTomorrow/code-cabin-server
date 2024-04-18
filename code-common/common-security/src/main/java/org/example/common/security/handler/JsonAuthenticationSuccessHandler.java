package org.example.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

/**
 *
 * @author ComeTomorrow
 * @since 2024/4/11
 */
public class JsonAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 登录成功后直接返回 JSON
     * @param request   请求
     * @param response  响应
     * @param authentication    成功认证的用户信息
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.OK.value());
        System.out.println("Success");
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), Result.success(ResultCode.SUCCESS));
    }
}
