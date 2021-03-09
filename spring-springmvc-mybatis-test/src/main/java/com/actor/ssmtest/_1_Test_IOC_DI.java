package com.actor.ssmtest;

import com.actor.ssmtest.dao.CustomerDao;
import com.actor.ssmtest.domain.Account;
import com.actor.ssmtest.domain.Customer1;
import com.actor.ssmtest.domain.Customer2;
import com.actor.ssmtest.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * description:
 *   最新(idea版) mybatis-spring-springmvc-Java一站式学习 -java框架-ssm框架-idea-更新完毕_哔哩哔哩 (゜-゜)つロ 干杯_-bilibili.html
 *   https://www.bilibili.com/video/BV1mE411X7yp?p=77
 *
 * IOC:
 *   通过Spring的IoC容器, 可以将对象间的依赖关系交由Spring进行控制, 避免硬编码所造成的过度程序耦合.
 *   用户也不必再为单例模式类\属性文件解析等这些很底层的需求编写代码, 可以更专注于上层的应用.
 *
 * AOP:
 *   通过Spring的AOP功能, 方便进行面向切面的编程, 许多不容易用传统OOP实现的功能可以通过AOP轻松应付.
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
public class _1_Test_IOC_DI {

    public static void main(String[] args) {
        /**
         * https://www.bilibili.com/video/BV1mE411X7yp?p=93
         * 2.核心容器的2个接口引发出的问题:
         *   @see ApplicationContext:
         *     它在构建核心容器时, 创建对象采取的策略是采用立即加载的方式. 也就是说, 只要一读取完配置文件马上就创建配置文件中的配置的对象.
         *     三个常用实现类:
         *       @see ClassPathXmlApplicationContext : 可以加载类路径下的配置文件, 要求配置文件必须在类路径下, 否则加载不了.
         *       2.FileSystemXmlApplicationContext   : 可以加载磁盘任意路径下的配置文件(必须有访问权限)
         *       3.AnnotationConfigApplicationContext: 用于读取注解创建容器.
         *
         * https://www.bilibili.com/video/BV1mE411X7yp?p=94
         *   @see BeanFactory:
         *     它在构建核心容器时, 创建对象采取的策略是采用延迟加载的方式. 也就是说, 什么时候根据id获取对象了, 什么时候才真正的创建对象.
         *     常用实现类:
         *       @see XmlBeanFactory:
         */
        //spring-context
        ApplicationContext ac = new ClassPathXmlApplicationContext("_1_TEST_IOC_DI.xml");
//        CustomerServiceImpl userServiceImpl = (CustomerServiceImpl) ac.getBean("customerService");//强转也可以
        CustomerServiceImpl customerService = ac.getBean("customerService", CustomerServiceImpl.class);
        CustomerDao customerDao = customerService.getCustomerDao();
        System.out.printf("ApplicationContext 获取的对象: %s\n", customerDao);


        Resource resource = new ClassPathResource("_1_TEST_IOC_DI.xml");        //spring-core
        BeanFactory factory = new XmlBeanFactory(resource);                     //spring-beans
        CustomerServiceImpl customerService2 = factory.getBean("customerService", CustomerServiceImpl.class);
        System.out.printf("BeanFactory 获取的对象: %s\n", customerService2);


        //构造函数注入示例
        Customer1 customerZs = ac.getBean("customer1", Customer1.class);
        System.out.println(customerZs);

        //set方法注入
        Customer2 customerLs = ac.getBean("customer2", Customer2.class);
        System.out.println(customerLs);

        //容器销毁
        ((ClassPathXmlApplicationContext) ac).close();//子类对象有这个方法
    }

//    public /*static*/ Account getAccount() {
//        return null;
//    }
}
