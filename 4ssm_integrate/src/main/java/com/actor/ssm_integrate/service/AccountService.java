package com.actor.ssm_integrate.service;

import com.actor.ssm_integrate.domain.Account;

import java.util.List;

/**
 * description: 描述
 * date       : 2021/4/20 on 23
 * @version 1.0
 */
public interface AccountService {

    List<Account> findAll();

    void saveAccount(Account account);
}
