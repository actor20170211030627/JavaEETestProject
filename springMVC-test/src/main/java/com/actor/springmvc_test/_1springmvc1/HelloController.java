package com.actor.springmvc_test._1springmvc1;

import com.actor.springmvc_test.domain.Account;
import com.actor.springmvc_test.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/hello")
@SessionAttributes(value = {"msg"})//将'msg=思思'存入到session域对象中
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
     *  浏览器输入(项目运行完会自动打开):
     *      http://localhost:8080/springmvc_day01_01_start/
     *      http://localhost:8080/springMVC_test_war/index.jsp (一样的)
     *  如果什么都没填: http://localhost:8080/
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
     */
    @RequestMapping(path = "/sayHello", method = RequestMethod.GET, headers = {"Accept"}, params = {"name=hehe"})
    public String sayHello() {
        //返回 success.jsp
        return "success";
    }

    /**
     * 3.请求参数绑定(post 请求中文不乱码): https://www.bilibili.com/video/BV1mE411X7yp?p=168
     * @see #testParam(String, String)
     */
    @RequestMapping("/testParam")
    public String testParam(String username, String password) {
        System.out.printf("username=%s, password=%s", username, password);
        return "success";
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=169
     * 1.请求参数绑定实体类型, 实体必须有 set()方法, 否则数据封装不进去...
     * 2.如果 Account 实体里面还有另外的实体 User, Account 就必须写 getUser 方法, 否则user封装不进去.
     * 3.post 请求中文乱码
     * @see #testAccount(Account)
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
     * 5.请求参数绑定 实体: https://www.bilibili.com/video/BV1mE411X7yp?p=171
     * 请求参数绑定集合类型 List & Map
     * 1.List & Map 必须有 get()方法, 否则数据封装不进去...
     *
     * 6.请求参数绑定 List&Map: https://www.bilibili.com/video/BV1mE411X7yp?p=172
     * 接收 Date 类型, SpringMVC默认接收格式: yyyy/MM/dd
     *
     * 6.1.自定义类型转换器: https://www.bilibili.com/video/BV1mE411X7yp?p=173
     * 1.写一个自定义类型转换器: {@link com.actor.springmvc_test.utils.String2DateConverter}
     * 2.在 springmvc.xml 中 配置自定义类型转换器
     * 3.在 springmvc.xml 中的 <mvn:annotation-driven 中添加属性: conversion-service
     */
    @RequestMapping("/testAccount")
    public String testAccount(Account account) {
        System.out.printf("account = %s", account);
        return "success";
    }

    /**
     * 7.获取Servlet原生的API: https://www.bilibili.com/video/BV1mE411X7yp?p=174
     * @see #testRequest(HttpServletRequest, HttpServletResponse)
     */
    @RequestMapping("/testRequest")
    public String testRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ServletContext servletContext = session.getServletContext();
        System.out.printf("request = %s, response = %s\n", request, response);
        System.out.printf("session = %s\n", session);
        System.out.printf("servletContext(最大域对象) = %s\n", servletContext);
        return "success";
    }

    /**
     * 8.请求参数: https://www.bilibili.com/video/BV1mE411X7yp?p=175
     * @see RequestParam 注解
     * @see RequestParam#name() 接收的参数名称
     * @see RequestParam#value() 同上
     * @see RequestParam#required() 是否必传, 默认true
     */
    @RequestMapping("/testRequestParam")
    public String testRequestParam(@RequestParam(value = "name12", required = true) String name) {
        System.out.printf("name = %s\n", name);
        return "success";
    }

    /**
     * 9.请求体: https://www.bilibili.com/video/BV1mE411X7yp?p=176
     * @see RequestBody 注解
     * 1.用于获取请求体内容, 直接使用(form表单)得到的是key=value&key1=value1...结构的数据.
     * 2.get请求方式不适用
     * 3.application/json;charset=UTF-8 发送json的形式, 可以自动转换成实体类
     * @see #testRequestBody(String)
     * @see #testRequestBody(User)
     */
    @RequestMapping(value = "/testRequestBody", method = RequestMethod.POST)
    public String testRequestBody(@RequestBody(required = true) String body) {
        //body = uname=%E5%8F%91%E6%96%AF%E8%92%82%E8%8A%AC&age=123123
        System.out.printf("body = %s\n", body);
        return "success";
    }
    //10.请求体, json转对象
    @RequestMapping(value = "/testRequestBodyJson2User", method = RequestMethod.POST)
    public String testRequestBody(@RequestBody User user) {
        System.out.printf("user = %s\n", user);
        return "success";
    }

    /**
     * 10.获取url参数: https://www.bilibili.com/video/BV1mE411X7yp?p=177
     * @see PathVariable 注解
     * @see PathVariable#value() 指定url中占位符名称.
     * @see PathVariable#required() 是否必须提供占位符.
     * 绑定url中的占位符. 例如: /delete/{id}, 这个{id}就是占位符.(从请求url中获取id)
     * url支持占位符是Spring 3.0中加入的, 是SpringMVC支持rest风格URL的一个重要标志.
     * @see #testPathVariable(String)
     */
    @RequestMapping(value = "/testPathVariable/{id}")
    public String testPathVariable(@PathVariable("id") String id) {
        System.out.printf("id = %s\n", id);
        return "success";
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=178
     * @see org.springframework.web.filter.HiddenHttpMethodFilter 过滤器(了解)
     * 由于浏览器form表单只支持GET/POST请求, 而DELETE/PUT 等method并不支持, Spring 3.0
     * 添加了一个过滤器, 可以将浏览器请求改为指定的请求方式, 发送给controller方法.
     * 1.在 web.xml 中配置过滤器.
     * 2.请求方式必须是post请求.
     * 3.按照要求提供_method 请求参数, 改参数的取值就是我们需要的请求方式.
     */
    /**
     * 12.RequestHeader 注解: https://www.bilibili.com/video/BV1mE411X7yp?p=179
     * @see RequestHeader 注解, 获取请求头的值
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader("Accept") String accept) {
        System.out.printf("Accept = %s\n", accept);
        return "success";
    }

    /**
     * 13.CookieValue 注解: https://www.bilibili.com/video/BV1mE411X7yp?p=180
     * @see CookieValue 注解, 获取cookie的值
     * JSESSIONID: jsp请求服务器, 服务器会生成JSESSIONID返回给浏览器.
     */
    @RequestMapping("/testCookieValue")
    public String testCookieValue(@CookieValue("JSESSIONID") String jSessionId) {
        System.out.printf("JSESSIONID = %s\n", jSessionId);
        return "success";
    }

    /**
     * 14.ModelAttribute 注解: https://www.bilibili.com/video/BV1mE411X7yp?p=181
     * @see ModelAttribute 注解
     * @see ModelAttribute#value() 用于获取数据的key, key可以是POJO的属性名称, 也可以是map结构的key.
     * 1.SpringMVC4.3 版本加入的, 用于'修饰方法和参数'.
     * 2.在方法上: 表示当前方法会在controller的方法执行前先执行.
     * 3.在参数上: 获取指定的数据给参数赋值.
     * 应用场景:
     *  当表单提交的数据不是完整的实体类数据时, 保证没有提交数据的字段使用数据库对象原来的数据.
     * @see #findUser(String) 1.先执行这个, 返回User
     * @see #testModelAttribute(User) 1.在执行这个, 把上一个方法返回的User放入这个参数.
     * @see #findUserMap(String, Map) 2.先执行这个, 没返回值, 把User放入Map
     * @see #testModelAttribute1(User) 2.从map的key中获取User
     */
    @RequestMapping("/testModelAttribute")
    public String testModelAttribute(User user) {
        System.out.printf("user = %s\n", user);
        return "success";
    }
    //从数据库根据uname查找, 返回User
    @ModelAttribute
    public User findUser(String uname) {
        System.out.println("先进了findUser()方法");
        User user = new User();
        user.setUname(uname);
        user.setAge(99);
        return user;
    }
    @RequestMapping("/testModelAttribute1")
    public String testModelAttribute1(@ModelAttribute("abc") User user) {
        System.out.printf("user = %s\n", user);
        return "success";
    }
    //从数据库根据uname查找, 没返回值
    @ModelAttribute
    public void findUserMap(String uname, Map<String, User> map) {
        System.out.println("先进了findUserMap()方法");
        User user = new User();
        user.setUname(uname);
        user.setAge(199);
        map.put("abc", user);
    }

    /**
     * 15.SessionAttributes 注解: https://www.bilibili.com/video/BV1mE411X7yp?p=182
     * @see SessionAttributes 注解, 和"Session域对象"有关, 只能写在类上.
     * @see SessionAttributes#value() 指定存入的属性名称
     * @see SessionAttributes#types() 指定存入的数据类型
     * 作用: 用于多次执行控制器方法间的参数共享
     * @see HelloController 的 @SessionAttributes("msg") 注解
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(/*HttpServletRequest request, */Model model) {
        //底层会存储到 request 域对象中.
        model.addAttribute("msg", "思思");
        model.addAttribute("msg1", "思思1");
        return "success";
    }
    @RequestMapping("/getSessionAttributes")//09:47
    public String getSessionAttributes(ModelMap modelMap) {
        Object msg = modelMap.getAttribute("msg1");
        System.out.printf("从msg1 = %s", msg);
        return "success";
    }
}
