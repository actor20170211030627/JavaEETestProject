package com.actor.springmvc_test._1springmvc1;

import com.actor.springmvc_test.exception.CustomException;
import com.actor.springmvc_test.exception.CustomExceptionResolver;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: 异常信息
 *
 * @author : 李大发
 * date       : 2021/4/18 on 17
 * @version 1.0
 */
@Controller
@RequestMapping("/exception")
public class _4Exception_Controller {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=196
     * SpringMVC"异常处理"之分析和搭建环境
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=198
     * SpringMVC异常处理之演示程序异常     int i = 10/0
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=200
     * SpringMVC异常处理之异常处理代码编写
     * 1.编写自定义异常类 {@link CustomException}
     * 2.编写异常处理器 {@link CustomExceptionResolver}
     * 3.在 pringmvc.xml 中配置异常处理器(跳转到提示页面):
     * <!-- 配置异常处理器(跳转到提示页面) -->
     * <bean id="customExceptionResolver" class="com.actor.springmvc_test.exception.CustomExceptionResolver"/>
     */
    @RequestMapping("/testException")
    public void testException() throws Exception {
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomException("除0异常了哟!!", e);
        } finally {
            //再制造非 CustomException 异常
            int j = 2 / 0;
        }
    }
}
