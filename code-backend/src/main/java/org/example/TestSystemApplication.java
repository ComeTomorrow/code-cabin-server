package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 系统模块
 *
 * @author ruoyi
 */
@SpringBootApplication
public class TestSystemApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(TestSystemApplication.class, args);
        System.out.println("系统模块启动成功");
    }
}
