package com.actor.ssmtest.service;

import com.actor.ssmtest.domain.Account;

import java.util.List;

/**
 * 注解测试Service
 */
public interface AccountService {

    //查询所有
    List<Account> findAllAccount();

    //根据id查询
    Account findAccountById(Integer accountId);

    //根据名称查询
    Account findAccountByName(String name);

    //保存
    void saveAccount(Account account);

    //更新
    void updateAccount(Account account);

    //根据id删除
    void deleteAccount(Integer accountId);

    //转钱
    void transferMoney(String from, String to, float money, boolean exception);
}
