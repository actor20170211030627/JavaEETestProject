package com.actor.mybatis_test.domain;

import com.actor.mybatis_test.utils.JacksonUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * description: 用户表
 * CREATE DATABASE IF NOT EXISTS `mybatis_test`;
 * USE `mybatis_test`;
 *
 * CREATE TABLE IF NOT EXISTS `user` (
 *  `id` int(11) NOT NULL AUTO_INCREMENT,
 *  `username` varchar(32) NOT NULL COMMENT '用户名称',
 *  `birthday` datetime DEFAULT NULL COMMENT '生日',
 *  `sex` char(1) DEFAULT NULL COMMENT '性别',
 *  `address` varchar(256) DEFAULT NULL COMMENT '地址',
 *  PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户';
 *
 * INSERT INTO `user` (`id`, `username`, `birthday`, `sex`, `address`) VALUES
 *  (1, '老王', '2018-02-27 22:52:29', '男', '北京'),
 *  (2, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 *  (3, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 *  (4, '传智播客', '2018-02-27 22:52:29', '男', '背景金燕龙'),
 *  (5, '老王', '2018-02-27 22:52:29', '男', '北京'),
 *  (6, '小马宝莉', '2018-02-27 22:52:29', '女', '北京修正');
 *
 * date       : 2021/5/4 on 08
 * @version 1.0
 */
public class User implements Serializable {

//    public Integer id;
//    public Integer age;
//    public String username;
//    public Date birthday;
//    public String sex;
//    public String address;

    //修改字段名, 和数据库字段不一致!
    public Integer userId;
    public Integer userAge;
    public String userName;
    public Date userBirthday;
    public String userSex;
    public String userAddress;

    @Override
    public String toString() {
        return JacksonUtils.object2Json(this);
    }
}
