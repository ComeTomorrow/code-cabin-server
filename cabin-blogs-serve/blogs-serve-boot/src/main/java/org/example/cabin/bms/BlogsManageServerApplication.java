package org.example.cabin.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BlogsManageServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(BlogsManageServerApplication.class, args);
    }
}
