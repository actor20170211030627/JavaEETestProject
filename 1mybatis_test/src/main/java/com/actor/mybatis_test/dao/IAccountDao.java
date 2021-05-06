package com.actor.mybatis_test.dao;

import com.actor.mybatis_test.domain.Account;
import com.actor.mybatis_test.domain.AccountUser;
import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.dto.AccountDto;

import java.util.List;

/**
 * description: 描述
 *
 * date       : 2021/5/5 on 17
 * @version 1.0
 */
public interface IAccountDao {

    List<Account> findAll();

    /**
     * 查询所有账号, 带有User用户的id, name, address
     * @return
     */
    List<AccountDto> findAllAccount1();

    //查询所有Account, 并把对应的User查出来
    List<AccountUser> findAccount2();

    List<UserAccounts> findAllUsers1();


    /**
     * 一对一查询, 延迟加载
     * @see com.actor.mybatis_test.service._7Load_Delay
     */
    List<AccountUser> findAllAccountDelay();
}
