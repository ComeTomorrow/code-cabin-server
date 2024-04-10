//package org.example.authentication.configure;
//
//import cn.binarywang.wx.miniapp.api.WxMaService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@Slf4j
//public class AuthorizationServerConfig {
//    @Autowired
//    private WxMaService wxMaService;
//
//    private MemberDetailsService memberDetailsService;
//
////    @Bean
////    public SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity httpSecurity){
////        httpSecurity
////                // 禁用basic明文验证
////                // 前后端分离架构不需要csrf保护
////                // 禁用默认登录页
////                // 禁用默认登出页
////                // 设置异常的EntryPoint，如果不设置，默认使用Http403ForbiddenEntryPoint
////                .exceptionHandling(exceptions -> exceptions.authenticationEntryPoint(invalidAuthenticationEntryPoint))
////                // 前后端分离是无状态的，不需要session了，直接禁用。
//////                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
////                        // 允许所有OPTIONS请求
////                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
////                        // 允许直接访问授权登录接口
////                        .requestMatchers(HttpMethod.POST, "/web/authenticate").permitAll()
////                        // 允许 SpringMVC 的默认错误地址匿名访问
////                        .requestMatchers("/error").permitAll()
////                        // 其他所有接口必须有Authority信息，Authority在登录成功后的UserDetailsImpl对象中默认设置“ROLE_USER”
////                        //.requestMatchers("/**").hasAnyAuthority("ROLE_USER")
////                        // 允许任意请求被已登录用户访问，不检查Authority
////                        .anyRequest().authenticated())
////                .authenticationProvider(authenticationProvider())
////                // 加我们自定义的过滤器，替代UsernamePasswordAuthenticationFilter
////                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
////
////        return httpSecurity.build();
////    }
//}