package com.actor.ssmtest.service.impl;

import com.actor.ssmtest.dao.AnnoService_TransactionManager_Anno;
import com.actor.ssmtest.dao.impl.AccountDaoImpl_TransactionManager_Anno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.SUPPORTS, readOnly = true /*, ...*/) //只读型事务的配置(查询)
public class AnnoServiceImpl_TransactionManager_Anno implements AnnoService_TransactionManager_Anno {

    @Autowired
    private AccountDaoImpl_TransactionManager_Anno accountDao;

    public void setAccountDao(AccountDaoImpl_TransactionManager_Anno accountDao) {
        this.accountDao = accountDao;
    }

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
