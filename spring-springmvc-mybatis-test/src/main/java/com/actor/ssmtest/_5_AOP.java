package com.actor.ssmtest;

import com.actor.ssmtest.config.SpringConfiguration;
import com.actor.ssmtest.controller.AccountController;
import com.actor.ssmtest.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

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
 * AOP相关术语
 *  1.Joinpoint(连接点):
 *      指那些被拦截到的点. 在spring中指的是方法, 因为spring只支持方法类型的连接点.
 *  2.Pointcut(切入点):
 *      指我们要对哪些Joinpoint进行拦截的定义.
 *      time: 1:22
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=122
 * https://www.bilibili.com/video/BV1mE411X7yp?p=123
 * https://www.bilibili.com/video/BV1mE411X7yp?p=124
 *     写一个工具类, 从线程中获取 Connection
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=125
 * https://www.bilibili.com/video/BV1mE411X7yp?p=126
 * https://www.bilibili.com/video/BV1mE411X7yp?p=127
 * https://www.bilibili.com/video/BV1mE411X7yp?p=128
 * https://www.bilibili.com/video/BV1mE411X7yp?p=129
 * https://www.bilibili.com/video/BV1mE411X7yp?p=130
 * https://www.bilibili.com/video/BV1mE411X7yp?p=131
 *     使用手写动态代理, 管理sql事务
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=132
 * AOP:
 *  通过Spring的AOP功能, 方便进行面向切面的编程, 许多不容易用传统OOP实现的功能可以通过AOP轻松应付.
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=133
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration.class)
public class _5_AOP {

    @Autowired
    private AccountController accountController;

    /**
     *
     */
    @Test
    public void transferMoney() {
        //转钱, 参4: 测试异常后sql事务
        accountController.transferMoney("aaa", "bbb", 100F, true);
    }

    //查询所有
    @Test
    public void findAllAccount() {
        List<Account> allAccount = accountController.findAllAccount();
        for (Account account : allAccount) {
            System.out.println(account);
        }
    }
}
