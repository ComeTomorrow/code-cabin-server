package org.example.gateway.configure;

import com.alibaba.csp.sentinel.adapter.gateway.sc.callback.GatewayCallbackManager;
import jakarta.annotation.PostConstruct;
import org.example.common.core.result.ResultCode;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * 自定义网关流控异常
 *
 * @author ComeTomorrow
 * @since 2024/3/28
 */
@Configuration
public class SentinelConfig {

    @PostConstruct
    private void initBlockHandler() {
        GatewayCallbackManager.setBlockHandler(
                (exchange, t) ->
                        ServerResponse
                                .status(HttpStatus.TOO_MANY_REQUESTS)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(
                                        BodyInserters.fromValue(ResultCode.FLOW_LIMITING.toString())
                                )
        );
    }
}