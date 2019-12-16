package com.binfo.zhangting.config;

import lombok.extern.java.Log;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 张挺（zhangting@binfo-tech.com）
 * @Description
 * @Date 2019/12/16 20:25
 */
public class MyIntercepter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor == preHandler ");
        long startTime = System.nanoTime();
        request.setAttribute("startTime", startTime);
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor == postHandle ");
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor == afterCompletion ");

        Long startTime = (Long) request.getAttribute("startTime");
        long endTime = System.nanoTime();
        // 打印出接口请求时间
        System.out.println("This method expired :" + (endTime - startTime) / 1000000 + " 毫秒");

        super.afterCompletion(request, response, handler, ex);
    }

}
