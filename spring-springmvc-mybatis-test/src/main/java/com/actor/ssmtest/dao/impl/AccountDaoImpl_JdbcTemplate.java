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

//@Repository
public class AccountDaoImpl_JdbcTemplate extends JdbcDaoSupport {

    @Autowired
    private JdbcTemplate jt;

    //1.通过代码获取 JdbcTemplate(不推荐)
    @Deprecated
    private JdbcTemplate getJt() {
        //1. Spring 自带数据源
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/spring_boot_test?characterEncoding=utf-8&serverTimezone=GMT%2B8");
        ds.setUsername("root");
        ds.setPassword("123456");
        JdbcTemplate jt = new JdbcTemplate();
        jt.setDataSource(ds);
        return jt;
    }

    /**
     * 0.新增,   execute: 执行操作
     */
    public void execute() {
        jt.execute("insert into account(name,money) values ('ddd', 1000)");
        System.out.println("操作完成!");
    }

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
     * 4.查询所有(自定义 RowMapper)
     */
    public void findAllCustom(Float money) {
        List<Account> accountsCustom = jt.query("select * from account where money >= ?", new AccountRowMapper(), money);
        for (Account account : accountsCustom) {
            System.out.println(account);
        }
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

    /**
     * 7.查询返回一行一列(使用聚合函数, 但不加group by子句)
     */
    public void selectCount(Float money) {
        Long count = jt.queryForObject("select count(*) from account where money >= ?", Long.class, money);
        System.out.printf("count = %d\n", count);
        System.out.println("操作完成!");
    }


    //定义 Account 的封装策略
    static class AccountRowMapper implements RowMapper<Account> {
        //把结果集中的数据封装到 Account 中, 然后spring把每个Account 加到集合中
        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setName(rs.getString("name"));
            account.setMoney(rs.getFloat("money"));
            return account;
        }
    }



    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=147
     * @see org.springframework.jdbc.core.support.JdbcDaoSupport 的使用以及Dao的两种编写方式
     * 1.Xml配置的方式: 见xml配置文件
     */
    public Account findAccountById(Integer accountId) {
        return getJdbcTemplate().queryForObject("select * from account where id = ?", new BeanPropertyRowMapper<>(Account.class), accountId);
    }
}
