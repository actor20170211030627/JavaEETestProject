package com.actor.ssmtest._9PlatformTransactionManager_Anno;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * https://www.bilibili.com/video/BV1mE411X7yp?p=154
 * spring基于注解的声明式事务控制
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:_9PlatformTransactionManager_Anno.xml")
public class PlatformTransactionManager_Anno1 {

    @Autowired
    private AnnoServiceImpl_Anno accountService;

    @Test
    public void findAll() {
        accountService.findAll(0F);//1000F
    }

    /**
     * 测试转账的事务: 异常
     */
    @Test
    public void transferMoney() {
        accountService.transferMoney("aaa", "bbb", 100F, true);//true: 制造异常
    }
}
