package org.example.gateway.filter;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTPayload;
import com.nimbusds.jose.JWSObject;
import org.example.common.core.constant.RedisConstants;
import org.example.common.core.constant.SecurityConstants;
import org.example.common.core.constant.TokenConstants;
import org.example.common.core.enums.ResultCode;
import org.example.common.core.utils.WebFluxUtils;
import org.example.common.redis.service.RedisCache;
import org.example.gateway.properties.IgnoreWhiteProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.text.ParseException;

/**
 * 网关鉴权
 *
 * @author ComeTomorrow
 */
@Component
public class AuthorizationFilter implements GlobalFilter, Ordered
{
    private static final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

    // 排除过滤的 uri 地址，nacos自行添加
    @Autowired
    private IgnoreWhiteProperties ignoreWhite;

    @Autowired
    private RedisCache redisCache;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain)
    {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();

        String url = request.getURI().getPath();
        String authorization = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // 跳过不需要验证的路径，若依中的白名单放行，个人认为与我现在的想法冲突，暂保意见。
//        if (StringUtils.matches(url, ignoreWhite.getWhites()))
//        {
//            return chain.filter(exchange);
//        }
        // 跳过不需要验证的路径，“有拉”项目的写法，获取请求头部信息，将没有令牌的直接放行。个人认为它的白名单放行是在认证中心实现的，暂保意见。
        if (StrUtil.isBlank(authorization)) {
            return chain.filter(exchange);
        }
        //获取令牌
        String token = getToken(authorization);
        if (StrUtil.isBlank(token))
        {
            return unauthorizedResponse(exchange, ResultCode.ACCESS_UNAUTHORIZED);
        }

        try {
            JWSObject jwsObject = JWSObject.parse(token);
            String jti = (String) jwsObject.getPayload().toJSONObject().get(JWTPayload.JWT_ID);
            Boolean isBlackToken = redisCache.hasKey(RedisConstants.TOKEN_BLACKLIST_PREFIX + jti);
            if (Boolean.TRUE.equals(isBlackToken)) {
                return unauthorizedResponse(exchange, ResultCode.TOKEN_ACCESS_FORBIDDEN);
            }
        } catch (ParseException e) {
            log.error("Parsing token failed in TokenValidationGlobalFilter", e);
            return unauthorizedResponse(exchange, ResultCode.TOKEN_INVALID);
        }

        return chain.filter(exchange);
//        Claims claims = JwtUtils.parseToken(token);
//        if (claims == null)
//        {
//            return unauthorizedResponse(exchange, "令牌已过期或验证不正确！");
//        }
//        String userkey = JwtUtils.getUserKey(claims);
//        boolean islogin = redisService.hasKey(getTokenKey(userkey));
//        if (!islogin)
//        {
//            return unauthorizedResponse(exchange, "登录状态已过期");
//        }
//        String userid = JwtUtils.getUserId(claims);
//        String username = JwtUtils.getUserName(claims);
//        if (StringUtils.isEmpty(userid) || StringUtils.isEmpty(username))
//        {
//            return unauthorizedResponse(exchange, "令牌验证失败");
//        }
//
//        // 设置用户信息到请求
//        addHeader(mutate, SecurityConstants.USER_KEY, userkey);
//        addHeader(mutate, SecurityConstants.DETAILS_USER_ID, userid);
//        addHeader(mutate, SecurityConstants.DETAILS_USERNAME, username);
//        // 内部请求来源参数清除
//        removeHeader(mutate, SecurityConstants.FROM_SOURCE);
//        return chain.filter(exchange.mutate().request(mutate.build()).build());
    }

//    private void addHeader(ServerHttpRequest.Builder mutate, String name, Object value)
//    {
//        if (value == null)
//        {
//            return;
//        }
//        String valueStr = value.toString();
//        String valueEncode = ServletUtils.urlEncode(valueStr);
//        mutate.header(name, valueEncode);
//    }

//    private void removeHeader(ServerHttpRequest.Builder mutate, String name)
//    {
//        mutate.headers(httpHeaders -> httpHeaders.remove(name)).build();
//    }

    private Mono<Void> unauthorizedResponse(ServerWebExchange exchange, ResultCode code)
    {
        log.error("[鉴权异常处理]请求路径:{}", exchange.getRequest().getPath());
        return WebFluxUtils.webFluxResponseWriter(exchange.getResponse(), HttpStatus.UNAUTHORIZED, code);
    }

    /**
     * 获取请求token
     */
    private String getToken(String authorization)
    {
        String token = null;
        // 如果前端设置了令牌前缀，则裁剪掉前缀
        if (StrUtil.isNotBlank(authorization) && authorization.startsWith(TokenConstants.PREFIX))
        {
            token = token.replaceFirst(TokenConstants.PREFIX, StrUtil.EMPTY);
        }
        return token;
    }

    @Override
    public int getOrder()
    {
        return -200;
    }
}