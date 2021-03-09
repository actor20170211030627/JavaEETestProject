package com.actor.ssmtest.dao;

public interface AnnoService_TransactionManager_Anno {

    void findAll(Float money);

    /**
     * 测试转账的事务: 异常
     */
    //单独配置 读写型事务配置
    void transferMoney(String from, String to, float money, boolean exception);
}
