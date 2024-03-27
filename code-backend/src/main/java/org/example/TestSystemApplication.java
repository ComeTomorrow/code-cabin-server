package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 系统模块
 *
 * @author ComeTomorrow
 */
@SpringBootApplication
public class TestSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TestSystemApplication.class, args);
        System.out.println(
                """
                ================  系统模块启动成功（￣︶￣）↗  ================        \s
                |   _____   ___   _____  ______            ___/--\\___    |       \s
                |  / ____/ / _ \\ |  __ \\|  ____/          //\\/ /\\ \\/\\\\   | \s
                | | |     / / \\ \\| |  | | |____         _//()\\ \\/ /()\\\\_ |  \s
                | | |    | (   ) | |  | |  ____|   <>    | _   __   _ |  |        \s
                | | |____ \\ \\_/ /| |__/ / |____          |(_) |* | (_)|  |      \s
                |  \\_____\\ \\___/ |_____/|______\\         |____|__|____|  |    \s
                |________________________________________________________|        \s
                | :: CodeCabin ::                              (v1.0.4)  |        \s
                ==========================================================        \s
                """
        );
    }
}
