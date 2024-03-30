package org.example.common.core.utils;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.result.Result;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import org.springframework.http.server.reactive.ServerHttpResponse;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * WebFlux 响应处理器
 *
 * @author ComeTomorrow
 * @since 2024/3/30
 */
@Slf4j
public class WebFluxUtils {

    /**
     * 设置webFlux模型响应
     *
     * @param response ServerHttpResponse
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, ResultCode code, Object value) {
        return webFluxResponseWriter(response, HttpStatus.OK, code, value);
    }

    /**
     * 设置webFlux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, ResultCode code) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON, status, code, null);
    }

    /**
     * 设置webFlux模型响应
     *
     * @param response ServerHttpResponse
     * @param status   http状态码
     * @param code     响应状态码
     * @param value    响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, HttpStatus status, ResultCode code, Object value) {
        return webFluxResponseWriter(response, MediaType.APPLICATION_JSON, status, code, value);
    }

    /**
     * 设置webFlux模型响应
     *
     * @param response    ServerHttpResponse
     * @param contentType content-type
     * @param status      http状态码
     * @param code        响应状态码
     * @param value       响应内容
     * @return Mono<Void>
     */
    public static Mono<Void> webFluxResponseWriter(ServerHttpResponse response, MediaType contentType, HttpStatus status, ResultCode code, Object value) {
        response.setStatusCode(status);
        response.getHeaders().setContentType(contentType);
//        response.getHeaders().add(httpHeaders,contentType.getType());
        Result<?> result = Result.result(code,value);
        String responseBody = JSONUtil.toJsonStr(result);
        DataBuffer dataBuffer = response.bufferFactory().wrap(responseBody.getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(dataBuffer));
//                .doOnError(error -> {
//                    DataBufferUtils.release(dataBuffer);
//                    log.error("Error writing response: {}", error.getMessage());
//                });
    }
}