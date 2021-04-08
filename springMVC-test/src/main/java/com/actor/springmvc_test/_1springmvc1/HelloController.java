package com.actor.springmvc_test._1springmvc1;

import com.actor.springmvc_test.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Controller
@RequestMapping("hello")
public class HelloController {

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
     * 2.配置"前端控制器(Servlet)", java/webapp/WEB-INF/web.xml => <servlet>
     *  并配置 filter, 解决 post 请求"中文乱码"问题
     *
     * 3.创建springmvc配置文件
     *  resource -> New -> XML ConfigurationFile -> Spring Config -> springmvc.xml
     *
     * 4.配置服务器
     *  Run/Debug Configurations => + => Tomcat Server => Deployment(部署) => + =>
     *  Artifact... => springMVC-test:war => 右侧Application context: springmvc_day01_01_start(项目路径, 用于http访问路径. 可填空)
     *  再点击 Deployment 左侧的Service, On 'Update' action: Redeploy(重新部署, 默认是Restart server)
     *  浏览器输入(项目运行完会自动打开): localhost:8080/springmvc_day01_01_start/
     *  如果什么都没填: : localhost:8080/
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=163
     *  1. index.jsp, HelloController
     *  2. 在 springmvc.xml 中开启扫描: <context:component-scan
     *  3. 在 web.xml 中配置        前端控制器 <servlet
     *  4. 在 web.xml 中配置        请求映射 <servlet-mapping
     *  5. 在 springmvc.xml 中配置  视图解析器 <bean, 用于解析跳转哪个.jsp
     *  6. 在 springmvc.xml 中开启SpringMVC框架注解的支持 <mvn:annotation-driven/>
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=164
     *  访问流程大概说明
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=165
     *  访问流程大概详细说明, SpringMVC框架基于组件方式执行流程↓: https://blog.csdn.net/qq_37989070/article/details/108895277.
     *  <a href="https://img-blog.csdnimg.cn/20201001155806721.png">流程图</a>
     *
     *  <mvn:annotation-driven/> 作用:
     *  在SpringMVC的各个组建中, 处理器映射器, 处理器适配器, 视图解析器称为SpringMVC的3大组件.
     *  使用<mvn:annotation-driven/> 自动加载 {@link RequestMappingHandlerMapping 处理映射器}
     *  和 {@link RequestMappingHandlerAdapter 处理适配器}, 可用在 SpringMVC.xml 配置文件中
     *  使用<mvn:annotation-driven/> 替代注解处理器和适配器的配置.
     *      它就相当于在xml中配置了:
     *      <!-- Begin -->
     *      <!-- HandlerMapping -->
     *      <bean ... ...一大堆, 详情看视频结尾
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=166
     * @see RequestMapping 请求映射
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=167
     * @see RequestMapping 注解的属性
     * @see RequestMapping#path()   映射路径, 和value()一样的作用
     * @see RequestMapping#value()  和path()一样的作用
     * @see RequestMapping#method() 指定请求方法, 例: {@link RequestMethod#GET}, get方法
     * @see RequestMapping#params() 指定限制请求参数的条件. 支持简单的表达式,
     *                              要求请求的key和value必须和配置的一模一样.
     *                              例: params = {"accountName"}, 请求参数必须有accountName
     *                              例: params = {"name=hehe"}, 请求参数必须有name=hehe
     *                              例: params = {"money!100"}, 请求参数中money不能是100
     * @see RequestMapping#headers() 指定限制请求消息头的条件.
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=168
     * 请求参数绑定(post 请求中文不乱码)
     * @see #testParam(String, String)
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=169
     * 1.请求参数绑定实体类型, 实体必须有 set()方法, 否则数据封装不进去...
     * 2.如果 Account 实体里面还有另外的实体 User, Account 就必须写 getUser 方法, 否则user封装不进去.
     * 3.post 请求中文乱码
     * @see #testAccount(Account)
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=170
     * 配置解决中文乱码的过滤器
     * <filter>
     *     <filter-name>characterEncodingFilter</filter-name>
     *     <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
     *     <init-param>
     *         <param-name>encoding</param-name>
     *         <param-value>UTF-8</param-value>
     *     </init-param>
     * </filter>
     * <filter-mapping>
     *     <filter-name>characterEncodingFilter</filter-name>
     *     <url-pattern>/*</url-pattern>
     * </filter-mapping>
     *
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=171
     * 请求参数绑定集合类型 List & Map
     * 1.List & Map 必须有 get()方法, 否则数据封装不进去...
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=172
     */
    @RequestMapping(path = "/sayHello", method = RequestMethod.GET, headers = {"Accept"}, params = {"name=hehe"})
    public String sayHello() {
        //返回 success.jsp
        return "success";
    }

    @RequestMapping("/testParam")
    public String testParam(String username, String password) {
        System.out.printf("username=%s, password=%s", username, password);
        return "success";
    }

    @RequestMapping("/testAccount")
    public String testAccount(Account account) {
        System.out.printf("account = %s", account);
        return "success";
    }
}
