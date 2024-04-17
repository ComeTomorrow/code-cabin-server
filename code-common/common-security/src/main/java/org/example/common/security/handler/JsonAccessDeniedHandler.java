package org.example.common.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 自定义无权限异常
 *
 * @author ComeTomorrow
 * @since 2024/4/11
 */
@Component
public class JsonAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), Result.failed(ResultCode.ACCESS_UNAUTHORIZED));
    }
}
