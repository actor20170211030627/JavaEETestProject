package com.actor.ssmtest._9PlatformTransactionManager_Anno;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true /*, ...*/) //只读型事务的配置(查询)
public class AnnoServiceImpl_Anno {

    @Autowired
    private AccountDaoImpl_Anno accountDao;

    public void findAll(Float money) {
        accountDao.findAll(money);
    }

    /**
     * 测试转账的事务: 异常
     */
    //单独配置 读写型事务配置
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false /*, ...*/)
    public void transferMoney(String from, String to, float money, boolean exception) {
        accountDao.transferMoney(from, to, money, exception);
    }
}
