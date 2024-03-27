package org.example.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 网关启动程序
 *
 * @author ComeTomorrow
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@EnableDiscoveryClient  //开启注册中心
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        System.out.println(
                """
                        ================  网关模块启动成功（￣︶￣）↗  ================ \s
                          ___    __   ____  ____  _    _    __   _  _\s
                         / __)  /__\\ (_  _)( ___)( \\/\\/ )  /__\\ ( \\/ )
                        ( (_ \\ /(__)\\  )(   )__)  )    (  /(__)\\ \\  /\s
                         \\___/(__)(__)(__) (____)(__/\\__)(__)(__)(__)\s
                        ========================================================== \s
                        """
        );
    }
}