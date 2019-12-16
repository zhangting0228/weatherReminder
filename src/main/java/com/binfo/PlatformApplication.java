package com.binfo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/1 17:21
 */
@SpringBootApplication
@EnableScheduling
@Controller
public class PlatformApplication {


    @RequestMapping(value = "/test")
    @ResponseBody
    public String home() {
        return "Hello Interceptor World";
    }

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}
