package com.actor.springmvc_test._1springmvc1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: 拦截器
 *
 * date       : 2021/4/18 on 22
 * @version 1.0
 */
@Controller
@RequestMapping("/interceptor")
public class _5InterceptorController {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=199
     * SpringMVC的处理器拦截器类似于Servlet中的过滤器Filter, 用于对处理器进行预处理和后处理.
     * 拦截器链(Interceptor Chain): 将拦截器按一定顺序联接成一条链. 在访问被拦截的方法或字段时,
     *      拦截器链中的拦截器就会按其之前定义的顺序被调用.
     * 拦截器和过滤器的区别:
     *  拦截器: 是SpringMVC框架自己的, 只有使用了SpringMVC框架的工程才使用.
     *          只会拦截访问"Controller"的方法, 不拦截: jsp, html, css, image, js
     *  过滤器: 是servlet规范中的一部分, 任何java web工程都可以使用.
     *          在url-pattern中配置了 /* 之后, 可以对所有要访问的资源拦截.
     *
     * 自定义拦截器, 必须实现 {@link org.springframework.web.servlet.HandlerInterceptor}
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=201
     * SpringMVC拦截器之编写controller
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=202
     * SpringMVC拦截器之拦截器入门代码
     * @see com.actor.springmvc_test.interceptor.CustomInterceptor0
     * 1.在springmvc.xml中配置拦截器
     * <!-- 配置拦截器(拦截controller方法) -->
     * <mvn:interceptors>
     *     <mvn:interceptor>
     *         <!-- 要拦截的具体方法, /** 拦截全部.   /hello/* 拦截hello里的所有方法 -->
     *         <mvn:mapping path="/interceptor/*"/>
     *         <!-- 不拦截的具体方法 -->
     *         <!--<mvn:exclude-mapping path=""/>-->
     *         <!-- 配置拦截器对象 -->
     *         <bean class="com.actor.springmvc_test.interceptor.CustomInterceptor"/>
     *     </mvn:interceptor>
     * </mvn:interceptors>
     *
     * 2.拦截器工作顺序
     *      1.自定义拦截器的 preHandle()方法执行了
     *      2.Controller中的方法执行了!
     *      3.自定义拦截器的 postHandle()方法执行了
     *      4.success.jsp里的java代码执行了!
     *      5.自定义拦截器的 afterCompletion()方法执行了
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=203
     * SpringMVC拦截器之拦截器接口方法演示
     * @see com.actor.springmvc_test.interceptor.CustomInterceptor1
     */
    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        System.out.println("2.Controller中的方法执行了!");
        return "success";
    }
}
