package com.binfo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/1 17:22
 */
public class ServletInitializer extends SpringBootServletInitializer
{
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(PlatformApplication.class);
    }
}
