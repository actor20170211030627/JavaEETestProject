package com.actor.ssm_integrate.service.impl;

import com.actor.ssm_integrate.dao.AccountDao;
import com.actor.ssm_integrate.domain.Account;
import com.actor.ssm_integrate.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    @Override
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @Override
    public void saveAccount(Account account) {
        accountDao.saveAccount(account);
    }
}
