package com.actor.ssm_integrate.dao;

import com.actor.ssm_integrate.domain.Account;

import java.util.List;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
public interface AccountDao {

    List<Account> findAll();

    void saveAccount(Account account);
}
