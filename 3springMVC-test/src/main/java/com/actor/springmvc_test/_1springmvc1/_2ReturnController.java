package com.actor.springmvc_test._1springmvc1;

import com.actor.springmvc_test.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description: 各种返回类型
 *
 * date       : 2021/4/15 on 22
 * @version 1.0
 */
@Controller
@RequestMapping("/return")
public class _2ReturnController {

    /**
     * 16.1.搭建环境: https://www.bilibili.com/video/BV1mE411X7yp?p=183
     *  响应之返回值是String类型: https://www.bilibili.com/video/BV1mE411X7yp?p=184
     */
    @RequestMapping("/returnString")
    public String returnString(Model model) {
        User user = new User();
        user.setUname("思思");
        user.setAge(123);
        model.addAttribute("user", user);//将user通过request域返回
        return "success";
    }

    /**
     * 2.响应之返回值是void类型: https://www.bilibili.com/video/BV1mE411X7yp?p=185
     * 什么都不放, 默认情况下点击会跳转:
     *      http://localhost:8080/springMVC_test_war/WEB-INF/pages/hello/returnVoid.jsp(会报错404)
     * 解决:
     *  请求转发: 服务器行为，request.getRequestDispatcher().forward(req,resp);是一次请求，转发后请求对象会保存，地址栏的URL地址不会改变。
     *  重定向: 客户端行为，response.sendRedirect(),从本质上讲等同于两次请求，前一次的请求对象不会保存，地址栏的URL地址会改变。
     *          java & jsp 都可以写重定向代码
     *  直接进行响应:
     *          response.getWriter().print("你好呀!");
     */
    @RequestMapping("/returnVoid")
    public void returnVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //请求转发, 转发到success页面
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request, response);


        //或者 重定向
//        response.sendRedirect("https://www.baidu.com/");
        //同样的效果
//        response.setStatus(response.SC_MOVED_TEMPORARILY);
//        response.setHeader("Location", "https://www.baidu.com/");

        //request.getContextPath()项目路径: "/springMVC_test_war"
//        response.sendRedirect(request.getContextPath() + "/index.jsp");

        //TODO: 2021/4/15 下方这个写法跳转cuccess没成功!!!
        response.sendRedirect(request.getContextPath() + "/WEB-INF/pages/hello/success.jsp");


        //直接进行响应
        //设置中文乱码(经测试, 可不写)
//        response.setCharacterEncoding("UTF-8");
        //设置浏览器打开解析的编码
//        response.setContentType("text/html;charset=UTF-8");
//        response.getWriter().print("你好呀, 这是返回void的直接进行响应!");
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=186
     * 响应之使用'forward'和'redirect'进行页面跳转
     */
    @RequestMapping("/testForwardOrredirect")
    public String testForwardOrredirect() {
        //请求转发
//        return "forward:/WEB-INF/pages/success.jsp";
        //重定向
//        return "redirect:/index.jsp";
        return "redirect:https://www.baidu.com/";
        //TODO: 2021/4/15 重定向到其它jsp
    }



    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=187
     * 响应json数据之过滤静态资源, 在springmvc.xml中配置:
     *  <!-- 前端控制器, 哪些静态资源不拦截, location: 静态资源地址. mapping: 哪些请求映射. -->
     *  <mvn:resources location="/css/" mapping="/css/**"/>
     *  <mvn:resources location="/js/" mapping="/js/**"/>
     *  <mvn:resources location="/image/" mapping="/image/**"/>
     *
     *  https://www.bilibili.com/video/BV1mE411X7yp?p=188
     *  返回值是 ModelAndView 类型
     */
    @RequestMapping("/returnModelAndView")
    public ModelAndView returnModelAndView() {
        ModelAndView mv = new ModelAndView();
        User user = new User();
        user.setUname("思思123");
        user.setAge(456);
        mv.addObject("user", user);//将user存入到mv对象中, 也会存入到request域对象
        mv.setViewName("success");//设置视图名称
        return mv;
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=189
     * 响应json数据之发送ajax请求
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=190
     * @see ResponseBody: 响应json数据之响应json格式数据, 写在 class/method 上
     * 需要 jackson 支持.
     * <dependency>
     *     <groupId>com.fasterxml.jackson.core</groupId>
     *     <artifactId>jackson-databind</artifactId>
     *     <version>2.9.10.7</version>
     *     <scope>compile</scope>
     * </dependency>
     */
    @ResponseBody
    @RequestMapping("/returnJson")
    public User returnJson(@RequestBody User user) {
        return user;
    }
}
