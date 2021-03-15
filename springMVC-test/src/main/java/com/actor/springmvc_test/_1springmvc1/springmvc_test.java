package com.actor.springmvc_test._1springmvc1;

//SpringMVC
public class springmvc_test {

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=159
     *  三层构架介绍和MVC设计模型介绍
     *
     *  SpringMVC底层: Servlet
     *
     *  2.1.Springmvc是什么
     * Spring web mvc和Struts2都属于"表现层"的框架,它是Spring框架的一部分,我们可以从Spring的整体结构中看得出来.
     * Springmvc 是Spring框架的一个模块, Springmvc和Spring("业务层") 无需通过中间整合层进行整合. //MyBatis 是"持久层"
     * Springmvc 是一个基于 mvc 的 web 框架.
     *   M model      模型,   例: JavaBean
     *   V view       视图,   例: JSP
     *   C controller 控制器, 例: Servlet
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=160
     *  SpringMVC框架的介绍
     *
     * 7.springmvc与struts2不同
     * 1、springmvc的入口是一个servlet即前端控制器，而struts2入口是一个filter过滤器。
     * 2、springmvc是基于方法开发(一个url对应一个方法)，请求参数传递到方法的形参，可以设计为单例或多例(建议单例)，
     * struts2是基于类开发，传递参数是通过类的属性，只能设计为多例。
     * 3、Struts采用值栈存储请求和响应的数据，通过OGNL存取数据，
     * springmvc通过参数解析器是将request请求内容解析，并给方法形参赋值，将数据和视图封装成ModelAndView对象，
     * 最后又将ModelAndView中的模型数据通过request域传输到页面。Jsp视图解析器默认使用jstl。
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=161
     * 入门程序之需求分析
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=162
     * 入门程序之搭建开发环境
     * 1.新建项目
     *   idea -> File -> New Module -> Maven -> Module SDK=1.8 -> ☑Create from archetype(原型)
     *   -> maven-archetype-webapp -> Propertis界面添加点击"＋"配置, 解决Maven构建下载jar构建慢的问题:key:"archetypeCatalog"  value:"internal"
     *   然后添加依赖, 见 pom.xml
     *
     *
     * 2.配置"前端控制器(Servlet)", java/webapp/WEB-INF/web.xml -> <servlet>
     *  并配置 filter, 解决 post 请求"中文乱码"问题
     *
     * 3.创建springmvc配置文件
     *  resource -> New -> XML ConfigurationFile -> Spring Config -> springmvc.xml
     *
     * 4.配置服务器
     *  Run/Debug Configurations -> Deployment(部署) ->
     *  配置server并且右侧Application context: springmvc_day01_01_start(项目路径, 用于http访问路径. 可填空)
     *  浏览器输入(项目运行完会自动打开): localhost:8080/springmvc_day01_01_start/
     *  如果什么都没填: : localhost:8080/
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=167
     * RequestMapping注解的属性
     */
    public static void main(String[] args) {
        System.out.println("hello springmvc!");
    }
}
