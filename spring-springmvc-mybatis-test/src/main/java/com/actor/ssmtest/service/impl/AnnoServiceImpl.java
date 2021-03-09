package com.actor.ssmtest.service.impl;

import com.actor.ssmtest.dao.AccountDao;
import com.actor.ssmtest.domain.Account;
import com.actor.ssmtest.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service/*("accountServiceImpl")*/
public class AnnoServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAllAccount() {
        return accountDao.findAllAccount();
    }

    @Override
    public Account findAccountById(Integer accountId) {
        return accountDao.findAccountById(accountId);
    }

    @Override
    public Account findAccountByName(String name) {
        return accountDao.findAccountByName(name);
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.updateAccount(account);
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountDao.deleteAccount(accountId);
    }

    //转账, 转钱
    @Override
    public void transferMoney(String from, String to, float money, boolean exception) {
        Account accountFrom = findAccountByName(from);
        Account accountTo = findAccountByName(to);
        accountFrom.setMoney(accountFrom.getMoney() - money);
        accountTo.setMoney(accountTo.getMoney() + money);
        updateAccount(accountFrom);
        //测试异常后sql事务
        if (exception) {
            int i = 1 / 0;
        }
        updateAccount(accountTo);
    }
}
