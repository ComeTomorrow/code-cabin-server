package org.example.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthenticationApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(AuthenticationApplication.class, args);
        System.out.println("""
                 ================  认证授权中心启动成功（￣︶￣）↗  ================   \s
                   _____          __  .__                   __  .__               __  .__              \s
                  /  _  \\  __ ___/  |_|  |__   ____   _____/  |_|__| ____ _____ _/  |_|__| ____   ____ \s
                 /  /_\\  \\|  |  \\   __\\  |  \\_/ __ \\ /    \\   __\\  |/ ___\\\\__  \\\\   __\\  |/ __ \\ /    \\\s
                /    |    \\  |  /|  | |   Y  \\  ___/|   |  \\  | |  \\  \\___ / __ \\|  | |  ( <__> )   |  \\
                \\____|__  /____/ |__| |___|  /\\___  >___|  /__| |__|\\___  >____  /__| |__|\\____/|___|  /
                        \\/                 \\/     \\/     \\/             \\/     \\/                    \\/   \s
                """);
    }
}
