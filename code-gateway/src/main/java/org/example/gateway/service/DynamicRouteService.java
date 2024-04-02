//package org.example.gateway.service;
//
//import com.alibaba.cloud.nacos.NacosConfigProperties;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.nacos.api.NacosFactory;
//import com.alibaba.nacos.api.PropertyKeyConst;
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import com.alibaba.nacos.api.exception.NacosException;
//import com.alibaba.nacos.client.naming.utils.CollectionUtils;
//import jakarta.annotation.PostConstruct;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
//import org.springframework.cloud.gateway.route.RouteDefinition;
//import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
//import org.springframework.cloud.gateway.support.NotFoundException;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.ApplicationEventPublisherAware;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Component;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Properties;
//import java.util.concurrent.Executor;
//
///**
// * 动态路由服务
// */
//@Component
//@Slf4j
//@RefreshScope
//public class DynamicRouteService implements ApplicationEventPublisherAware {
//
//    @Autowired
//    private RouteDefinitionWriter routeDefinitionWriter;
//
//    @Autowired
//    private NacosConfigProperties nacosConfigProperties;
//
//    private ApplicationEventPublisher publisher;    /* nacos 配置服务 */
//
//    private ConfigService configService;    /* 应用事件发布器 */
//
//    /**
//     * 获取 nacos 配置服务
//     *
//     * @return
//     * @throws NacosException
//     */
//    private ConfigService getConfigService() throws NacosException {
//        // 懒汉加载，用到再创建这个服务
//        if (configService == null) {
//            Properties properties = new Properties();
//            properties.setProperty(PropertyKeyConst.SERVER_ADDR,nacosConfigProperties.getServerAddr());
//            properties.setProperty(PropertyKeyConst.USERNAME, nacosConfigProperties.getUsername());
//            properties.setProperty(PropertyKeyConst.PASSWORD, nacosConfigProperties.getPassword());
//            properties.setProperty(PropertyKeyConst.NAMESPACE, nacosConfigProperties.getNamespace());
//            configService = NacosFactory.createConfigService(properties);
//        }
//        return configService;
//    }
//
//    /**
//     * 网关路由初始化
//     */
//    @PostConstruct
//    public void initGatewayRouters(){
//        try {
//            // 配置ID
//            String dateId = "gateway.yaml";
//            // 分组
//            String group = "DEFAULT_GROUP";
//
//            // 获取当前Nacos里，对应dataId的配置数据，随后开始监听对应dataId数据变更
//            String configJson = getConfigService().getConfigAndSignListener(dateId, group, nacosConfigProperties.getTimeout(), new Listener() {
//                @Override
//                public Executor getExecutor() { return null; }
//
//                /**
//                 * 数据变更时触发
//                 * @param configInfo 新的配置信息
//                 */
//                @Override
//                public void receiveConfigInfo(String configInfo) {
//                    System.out.println(configInfo);
//                    addRoute(configInfo);
//                }
//            });
//            addRoute(configJson);
//        } catch (Exception e) {
//
//        }
//    }
//
//    @Override
//    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
//        this.publisher = applicationEventPublisher;
//    }
//
//    private void publish(){
//        this.publisher.publishEvent(new RefreshRoutesEvent(this));
//    }
//
//    //增加路由
//    public String addRoute(String configJson) {
//        List<RouteDefinition> definitions = JSONArray.parseArray(configJson, RouteDefinition.class);
//        if (!CollectionUtils.isEmpty(definitions)){
//            // 遍历更新路由信息
//            for (RouteDefinition definition : definitions){
//                routeDefinitionWriter.save(Mono.just(definition)).subscribe();
//            }
//            // 发送刷新路由的事件通知
//            publish();
//        }
//        return "success";
//    }
//
//    //更新路由
////    public String updateRoute(RouteDefinition definition) {
////        try {
////            deleteRoute(definition.getId());
////        } catch (Exception e) {
////            return "update fail, not find route routeId: " + definition.getId();
////        }
////        try {
////            return addRoute(definition);
////        } catch (Exception e) {
////            return "update route  fail";
////        }
////    }
//
//    //删除路由
//    public Mono<ResponseEntity<Object>> deleteRoute(String id) {
//        return this.routeDefinitionWriter.delete(Mono.just(id))
//                .then(Mono.defer(() -> Mono.just(ResponseEntity.ok().build())))
//                .onErrorResume((t) -> t instanceof NotFoundException, (t) -> Mono.just(ResponseEntity.notFound().build()));
//    }
//}