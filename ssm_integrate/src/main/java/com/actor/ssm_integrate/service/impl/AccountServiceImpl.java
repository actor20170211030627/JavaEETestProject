package com.actor.ssm_integrate.service.impl;

import com.actor.ssm_integrate.domain.Account;
import com.actor.ssm_integrate.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
@Service("account")
public class AccountServiceImpl implements AccountService {
    @Override
    public List<Account> findAll() {
        System.out.println("AccountServiceImpl: 查询所有");
        return null;
    }

    @Override
    public void saveAccount(Account account) {
        System.out.println("AccountServiceImpl: 查询所有");
    }
}
