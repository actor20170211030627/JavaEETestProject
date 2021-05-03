package com.actor.ssmtest._2Test_IOC_DI_Anno.dao;

import com.actor.ssmtest.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository/*("accountDaoImpl_Dbutils")*/
public class AccountDaoImpl_Dbutils implements AccountDao {

    @Autowired
    private QueryRunner runner;

    @Override
    public List<Account> findAllAccount() {
        try {
            return runner.query("select * from account", new BeanListHandler<>(Account.class));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public Account findAccountById(Integer accountId) {
        try {
            return runner.query("select * from account where id = ?", new BeanHandler<>(Account.class), accountId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    //select * from account where name like '%aaa%'
    @Override
    public Account findAccountByName(String name) {
        name = "%" + name + "%";
        try {
            return runner.query("select * from account where name like ?", new BeanHandler<>(Account.class), name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            runner.update("insert into account(name, money) values(?,?)", account.getName(), account.getMoney());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateAccount(Account account) {
        try {
            runner.update("update account set name=?, money=? where id = ?", account.getName(), account.getMoney(), account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteAccount(Integer accountId) {
        try {
            runner.update("delete from account where id = ?", accountId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
