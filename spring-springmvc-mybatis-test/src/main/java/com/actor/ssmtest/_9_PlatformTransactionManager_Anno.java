package com.actor.ssmtest;

import com.actor.ssmtest.dao.AnnoService_TransactionManager_Anno;
import com.actor.ssmtest.dao.impl.AccountDaoImpl_TransactionManager_Anno;
import com.actor.ssmtest.service.impl.AnnoServiceImpl_TransactionManager_Anno;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * https://www.bilibili.com/video/BV1mE411X7yp?p=154
 * spring基于注解的声明式事务控制
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(/*classes = _9_PlatformTransactionManager_Anno.SpringConfiguration.class*/
//        locations = "classpath:_9_PlatformTransactionManager_Anno.xml")
public class _9_PlatformTransactionManager_Anno {

    @Autowired
    private static AnnoService_TransactionManager_Anno accountService;

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("_9_PlatformTransactionManager_Anno.xml");
        accountService = ac.getBean("accountService", AnnoServiceImpl_TransactionManager_Anno.class);
        AccountDaoImpl_TransactionManager_Anno accountDao = ac.getBean("accountDao", AccountDaoImpl_TransactionManager_Anno.class);
        ((AnnoServiceImpl_TransactionManager_Anno) accountService).setAccountDao(accountDao);

        findAll();
    }

//    @Test
    public static void findAll() {
        accountService.findAll(0F);//1000F
    }

    /**
     * 测试转账的事务: 异常
     */
//    @Test
    public void transferMoney() {
        accountService.transferMoney("aaa", "bbb", 100F, true);//true: 制造异常
    }

//    @Configuration//这个注解可写可不写, 因为这个类会作为字节码参数传给 AnnotationConfigApplicationContext 对象.
//    @ComponentScan({"com.actor.ssmtest.dao.impl", "com.actor.ssmtest.config"})//配置要扫描的包
//    @Import({JdbcConfig.class, TransactionConfig.class})
//    @PropertySource("classpath:application.properties")
//    @EnableTransactionManagement   //开启spirng对注解事务的支持
//    public /*static*/ class SpringConfiguration {
//
//    }
}

























