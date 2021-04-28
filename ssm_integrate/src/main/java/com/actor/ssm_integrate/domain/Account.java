package com.actor.ssm_integrate.domain;

import java.io.Serializable;

/**
 * description: 创建数据库, 账户表
 * CREATE DATABASE ssm_test;
 * USE ssm_test;
 * CREATE TABLE `account` (
 * 	`id` INT(11) NOT NULL AUTO_INCREMENT,
 * 	`name` VARCHAR(20) NULL DEFAULT NULL,
 * 	`money` DOUBLE NULL DEFAULT NULL,
 * 	PRIMARY KEY (`id`)
 * )
 * COMMENT='账户'
 *
 *
 * @author : 李大发
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
public class Account implements Serializable {

    public Integer id;
    public String name;
    public Double money;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
}
