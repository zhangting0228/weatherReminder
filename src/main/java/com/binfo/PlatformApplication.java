package com.binfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/1 17:21
 */
@SpringBootApplication
@EnableScheduling
public class PlatformApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
