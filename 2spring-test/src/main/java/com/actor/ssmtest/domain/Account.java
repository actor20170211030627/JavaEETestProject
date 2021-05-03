package com.actor.ssmtest.domain;

import com.actor.ssmtest.utils.JacksonUtils;

import java.io.Serializable;

/**
 * create table account(
 *          id int primary key auto_increment,
 *          name varchar(40),
 *          money float
 * )character set utf8 collate utf8_general_ci;
 *
 * insert into account(name, money) values('aaa', 1000);
 * insert into account(name, money) values('bbb', 1000);
 * insert into account(name, money) values('ccc', 1000);
 */
public class Account implements Serializable {

    private static final long serialVersionUID = -1348442828667507781L;
    private int id;
    private String name;
    private Float money;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
