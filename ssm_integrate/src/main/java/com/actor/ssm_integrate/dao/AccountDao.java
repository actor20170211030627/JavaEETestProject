package com.actor.ssm_integrate.dao;

import com.actor.ssm_integrate.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description: 描述
 *
 * @author : 李大发
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
@Repository
public interface AccountDao {

    /**
     * 查询所有账号, 这儿懒得写映射, 用注解的方式.
     */
    @Select("select * from account")
    List<Account> findAll();

    @Insert("insert into account (name, money) values (#{name}, #{money})")
    void saveAccount(Account account);
}
