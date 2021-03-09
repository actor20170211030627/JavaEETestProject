package com.actor.ssmtest.dao.impl;

import com.actor.ssmtest.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoImpl_TransactionManager {

    @Autowired
    private JdbcTemplate jt;

    /**
     * 1.新增
     */
    public void insert() {
        jt.update("insert into account(name,money) values (?,?)", "eee", 3333F);
        System.out.println("操作完成!");
    }

    /**
     * 2.更新
     */
    public void update(Account account) {
        jt.update("update account set name=?, money=? where id=?", account.getName(), account.getMoney(), account.getId());
        System.out.println("操作完成!");
    }

    /**
     * 3.删除
     */
    public void delete(int accountId) {
        jt.update("delete from account where id = ?", accountId);
        System.out.println("操作完成!");
    }

    /**
     * 5.查询 "一个/所有" (Spring 自带 BeanPropertyRowMapper)
     */
    public void findAll(Float money) {
        List<Account> accounts = jt.query("select * from account where money >= ?", new BeanPropertyRowMapper<>(Account.class), money);
        for (Account account : accounts) {
            System.out.println(account);
        }
        System.out.println("操作完成!");
    }

    /**
     * 6.查询一个
     */
    public Account findOne(int accountId) {
        //这种查询List也可以
//        List<Account> account = jt.query("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class), accountId);
//        for (Account account1 : account) {
//            System.out.println(account1);
//        }
        return jt.queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class), accountId);
    }

    //根据名字查询(如果有多个结果, 抛异常)
    public Account findAccountByName(String name) {
        name = "%" + name + "%";
        return jt.queryForObject("select * from account where name like ?", new BeanPropertyRowMapper<>(Account.class), name);
    }

    /**
     * 7.查询返回一行一列(使用聚合函数, 但不加group by子句)
     */
    public void selectCount(Float money) {
        Long count = jt.queryForObject("select count(*) from account where money >= ?", Long.class, money);
        System.out.printf("count = %d\n", count);
        System.out.println("操作完成!");
    }

    /**
     * 转账, 转钱
     * @param from
     * @param to
     * @param money
     * @param exception
     */
    public void transferMoney(String from, String to, float money, boolean exception) {
        Account accountFrom = findAccountByName(from);
        Account accountTo = findAccountByName(to);
        accountFrom.setMoney(accountFrom.getMoney() - money);
        accountTo.setMoney(accountTo.getMoney() + money);

        update(accountFrom);
        //测试异常后sql事务
        if (exception) {
            int i = 1 / 0;
        }
        update(accountTo);
    }
}
