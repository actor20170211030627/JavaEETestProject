package com.actor.ssmtest._5AOP_Xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: AOP: Aspect Oriented Programming(面向切面编程)
 *      通过预编译方式和运行期动态代理实现程序功能的统一维护的一种技术.
 *      AOP是OOP(面向对象)的延续, 是软件开发中的一个热点, 也是Spring框架中的一个重要内容,
 * 是函数式编程的一种衍生规范.
 *      利用Aop可以对业务逻辑的各个部分进行隔离, 从而使得业务逻辑各部分之间的耦合度降低,
 * 提高程序的可重用性, 同事提高了开发的效率.
 *
 *      简单的说它就是把我们程序重复的代码抽取出来, 在需要执行的时候, 使用动态代理的技术,
 * 在不修改源代码的基础上, 对我们的已有方法进行增强.
 *
 * 作用: 在程序运行期间, 不修改远吗对已有方法进行增强.
 * 优势:
 *      减少重复代码
 *      提高开发效率
 *      维护方便
 * AOP实现方式: 使用动态代理技术
 *
 * AOP相关术语(p=133)
 *  1.Joinpoint(连接点):
 *      指那些被拦截到的点. 在spring中指的是方法, 因为spring只支持方法类型的连接点.
 *  2.Pointcut(切入点):
 *      指我们要对哪些Joinpoint进行拦截的定义.
 *  3.Advice(通知/增强):
 *      指拦截到Joinpoint之后所要做的事情.
 *      通知类型: 前置通知, 后置通知, 异常通知, 最终通知, 环绕通知.
 *  4.Introduction(引介):
 *      引介是一种特殊的通知在不修改类代码的前提下,
 *      Introduction 可以在运行期为类动态地添加一些方法或Field.
 *  5.Target(目标对象):
 *      代理的目标对象.
 *  6.Weaving(织入):
 *      指把增强应用到目标对象来创建新的代理对象的过程.
 *      spring采用动态代理织入, 而AspectJ采用编译器织入和类装载期织入.
 *  7.Proxy(代理):
 *      一个类被AOP织入增强后, 就产生一个结果代码类.
 *  8.Aspect(切面):
 *      切入点和通知(引介)的结合.
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=122
 *  今日课程内容介绍
 * https://www.bilibili.com/video/BV1mE411X7yp?p=123
 *  案例中添加转账方法并演示事务问题
 * https://www.bilibili.com/video/BV1mE411X7yp?p=124
 *  分析事务的问题并编写ConnectionUtils(一个工具类, 从线程中获取 Connection)
 * https://www.bilibili.com/video/BV1mE411X7yp?p=125
 *  编写事务管理工具类并分析连接和线程绑定
 * https://www.bilibili.com/video/BV1mE411X7yp?p=126
 *  编写业务层和持久层事务控制代码并配置spring的ioc
 * https://www.bilibili.com/video/BV1mE411X7yp?p=127
 *  测试转账并分析案例中的问题
 * https://www.bilibili.com/video/BV1mE411X7yp?p=128
 *  代理的分析
 * https://www.bilibili.com/video/BV1mE411X7yp?p=129
 *  基于接口的动态代理回顾
 * https://www.bilibili.com/video/BV1mE411X7yp?p=130
 *  基于子类的动态代理
 * https://www.bilibili.com/video/BV1mE411X7yp?p=131
 *  使用动态代理实现事务控制(管理sql事务)
 * https://www.bilibili.com/video/BV1mE411X7yp?p=132
 * AOP的概念:
 *  通过Spring的AOP功能, 方便进行面向切面的编程, 许多不容易用传统OOP实现的功能可以通过AOP轻松应付.
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=133
 *  spring中aop术语和细节
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=134
 *  spring基于XML的AOP-编写必要的代码
 *      1.导入依赖
 *          spring-context,
 *          <dependency>
 * 	            <groupId>org.aspectj</groupId>
 * 	            <artifactId>aspectjweaver</artifactId>
 * 	            <version>1.8.7</version>
 *         </dependency>
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=135
 *  spring基于XML的AOP-配置步骤
 *      1.spring中基于 XML 的 AOP 配置步骤, 见xml配置文件
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=136
 *  切入点表达式的写法, 见xml
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=137
 *  四种常用通知类型, 见xml
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=138
 *  通用化切入点表达式, 见xml中 <aop:pointcut
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=139
 *  spring中的"环绕通知", 见xml中 <aop:around
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */

public class AOP_Xml {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("_5AOP_Xml.xml");
        Aop_Bean aop_bean = ac.getBean("aop_Bean", Aop_Bean.class);

        testAop(aop_bean);
    }

    //测试AOP切面
    public static void testAop(Aop_Bean aop_bean) {
        aop_bean.saveAccount();
        aop_bean.updateAccount(1);
        aop_bean.deleteAccount();
    }
}
