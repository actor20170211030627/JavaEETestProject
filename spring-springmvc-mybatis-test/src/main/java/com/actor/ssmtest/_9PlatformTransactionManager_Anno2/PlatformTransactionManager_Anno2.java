package com.actor.ssmtest._9PlatformTransactionManager_Anno2;

import com.actor.ssmtest._9PlatformTransactionManager_Anno.AnnoServiceImpl_Anno;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * https://www.bilibili.com/video/BV1mE411X7yp?p=155
 *  spring基于纯注解的声明式事务控制
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=156
 *  spring编程式事务控制1-了解
 *  代码实现事务控制, 回滚 等.
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=157
 *  spring编程式事务控制2-了解
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfiguration9.class)
public class PlatformTransactionManager_Anno2 {

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
