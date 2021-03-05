package com.actor.ssmtest.dao;

import com.actor.ssmtest.domain.Account;

import java.util.List;

public interface AccountDao {

    //查询所有
    List<Account> findAllAccount();

    //根据id查询
    Account findAccountById(Integer accountId);

    Account findAccountByName(String name);

    //保存
    void saveAccount(Account account);

    //更新
    void updateAccount(Account account);

    //根据id删除
    void deleteAccount(Integer accountId);
}
