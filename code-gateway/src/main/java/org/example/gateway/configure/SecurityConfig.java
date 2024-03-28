package org.example.gateway.configure;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 客户端配置
 *
 * @author ComeTomorrow
 * @since 2024/3/28
 */
@ConfigurationProperties(prefix = "security")
@Configuration(proxyBeanMethods = false)
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig {

    /**
     * 黑名单请求路径列表
     */
    @Setter
    private List<String> blacklistPaths;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                .authorizeExchange(exchange ->
                        {
                            if (!CollectionUtils.isEmpty(blacklistPaths)){
                                exchange.pathMatchers(blacklistPaths.toArray(String[]::new)).authenticated();
                            }
                            exchange.anyExchange().permitAll();
                        }
                )
                .csrf(ServerHttpSecurity.CsrfSpec::disable);
        return httpSecurity.build();
    }

}