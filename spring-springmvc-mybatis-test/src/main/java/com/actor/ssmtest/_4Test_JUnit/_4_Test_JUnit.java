package com.actor.ssmtest._4Test_JUnit;

import com.actor.ssmtest._3Test_IOC_DI_Anno.SpringConfiguration3;
import com.actor.ssmtest.controller.AccountController;
import com.actor.ssmtest.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description: 注解方式注入
 *  https://www.bilibili.com/video/BV1mE411X7yp?p=120
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration3.class)
public class _4_Test_JUnit {

    @Autowired
    private AccountController accountController;

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=120
     *      spring整合junit问题分析
     * https://www.bilibili.com/video/BV1mE411X7yp?p=121 :spring整合junit完成
     * 1.导入jar包
     * <!-- 如果 spring 5.x 版本的时候, 要求junit的jar必须是 4.12+, 否则 测试方法 会报错 -->
     * <dependency>
     *     <groupId>junit</groupId>
     *     <artifactId>junit</artifactId>
     *     <version>4.12</version>
     *     <scope>compile</scope>
     * </dependency>
     *
     * @see RunWith
     *      使用 junit 的@RunWith 注解, 替换掉 junit 里的 main 方法成 spring-test 的 main 方法, 有容器功能
     * @see ContextConfiguration(locations = {""}, classes = {...})
     *      属性:
     *          locations: 指定xml文件的位置, 加上 classpath 关键字, 表示在类路径下. 例: locations = "classpath:bean.xml"
     *          classes:   指定注解类所在位置, 例: classes = SpringConfiguration.class
     *      注意:
     *          如果 spring 5.x 版本的时候, 要求junit的jar必须是 4.12+, 否则 测试方法 会报错
     */
    @Test
    public void findByName() {
        Account account = accountController.findByName();
        System.out.println(account);
    }
}
