package com.actor.springmvc_test.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * description: 异常处理器
 *
 * @author : 李大发
 * date       : 2021/4/18 on 20
 * @version 1.0
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

    /**
     * 处理异常
     * @param request 请求
     * @param response 响应
     * @param handler 当前处理器对象
     * @param ex 异常
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        CustomException ce;
        if (ex instanceof CustomException) {
            ce = (CustomException) ex;
        } else {
            ce = new CustomException("CustomException未知错误:" + ex.getMessage(), ex);
        }
        ModelAndView mv = new ModelAndView();
        mv.addObject("errMsg", ce);//错误信息
        mv.setViewName("error");   //跳转error页面
        return mv;
    }
}
