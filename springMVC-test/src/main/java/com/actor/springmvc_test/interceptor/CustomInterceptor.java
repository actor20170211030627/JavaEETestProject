package com.actor.springmvc_test.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 自定义拦截器
 *
 * @author : 李大发
 * date       : 2021/4/18 on 23
 * @version 1.0
 */
public class CustomInterceptor implements HandlerInterceptor {

    /**
     * 预处理, controller方法前执行
     * @param request 请求
     * @param response 响应
     * @param handler
     * @return true: 放行, 执行下一个拦截器. 如果没有, 执行controller方法
     *         false: 不放行, 可用request/response跳转页面等(对不起, 您没权限...)
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("1.自定义拦截器的 preHandle()方法执行了");
        return true;
    }

    /**
     * 后处理
     * @param request 请求
     * @param response 响应
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("3.自定义拦截器的 postHandle()方法执行了");
    }

    /**
     * 页面走完才走这个方法
     * @param request 请求
     * @param response 响应
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("5.自定义拦截器的 afterCompletion()方法执行了");
    }
}
