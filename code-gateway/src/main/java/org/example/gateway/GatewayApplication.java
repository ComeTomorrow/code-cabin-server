package org.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 *
 * @author ComeTomorrow
 * @since 2024/3/24
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient  //开启注册中心
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println(
                """
                        ================  网关模块启动成功（￣︶￣）↗  ================ \s
                                       __                              \s
                           _________ _/  |_  ______  _  _______  ___.__.
                          / ___\\__  \\\\   __\\/ __ \\ \\/ \\/ /\\__  \\<   |  |
                         / /_/  > __ \\|  | \\  ___/\\     /  / __ \\\\___  |
                         \\___  (____  /__|  \\___  >\\/\\_/  (____  / ____|
                        /_____/     \\/          \\/             \\/\\/    \s
                        ========================================================== \s
                        """
        );
    }
}