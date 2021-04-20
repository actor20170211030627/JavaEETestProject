package com.actor.springmvc_test.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 自定义拦截器0
 *
 * date       : 2021/4/18 on 23
 * @version 1.0
 */
public class CustomInterceptor0 implements HandlerInterceptor {

    public int pos;
    /**
     * 标记这是第几个拦截器
     */
    public void setPos(int pos) {
        this.pos = pos;
    }

    /**
     * 预处理, controller方法前执行
     * @param request 请求
     * @param response 响应
     * @param handler
     * @return true: 放行, 执行下一个拦截器. 如果没有, 执行controller方法
     *         false: 不放行, 不执行后面的步骤了. 可用request/response跳转页面等(对不起, 您没权限...)
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String queryString = request.getQueryString();
        //release: 放行
        boolean release = queryString.contains("release=true");
        System.out.printf("1.自定义拦截器 (%d) 的 preHandle()方法执行了, 放行: %b\n", pos, release);
        //如果放行
        if (release) {
            request.setAttribute("msg" + pos, "拦截器 (" + pos + ") 放行!");
        } else {
            request.setAttribute("errMsg" + pos, "对不起, 被拦截器 (" + pos + ") 拦截了!");
            //请求转发
            request.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(request, response);
        }
        return release;
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
        System.out.printf("3.自定义拦截器 (%d) 的 postHandle()方法执行了\n", pos);
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
        System.out.printf("5.自定义拦截器 (%d) 的 afterCompletion()方法执行了\n", pos);
    }
}
