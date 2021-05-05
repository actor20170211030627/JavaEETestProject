package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;

/**
 * description: 账户表
 * CREATE TABLE `account` (
 * 	`ID` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
 * 	`UID` INT(11) NULL DEFAULT NULL COMMENT '用户编号',
 * 	`MONEY` DOUBLE NULL DEFAULT NULL COMMENT '金额',
 * 	PRIMARY KEY (`ID`) USING BTREE,
 * 	INDEX `FK_Reference_8` (`UID`) USING BTREE,
 * 	CONSTRAINT `FK_Reference_8` FOREIGN KEY (`UID`) REFERENCES `mybatis_test`.`user` (`id`) ON UPDATE RESTRICT ON DELETE RESTRICT
 * )
 * COMMENT='账户'
 * COLLATE='utf8_general_ci'
 * ENGINE=InnoDB
 * AUTO_INCREMENT=4
 * ;
 *
 * INSERT INTO `account` (`ID`, `UID`, `MONEY`) VALUES ('1', '5', '1000.23'),('2', '2', '23.4567'),('3', '5', '258.63');
 *
 * date       : 2021/4/25 on 23
 * @version 1.0
 */
public class Account implements Serializable {

    public Integer id;
    public Integer uid;
    public Double money;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
