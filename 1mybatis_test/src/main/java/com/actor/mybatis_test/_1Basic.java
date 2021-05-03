package com.actor.mybatis_test;

/**
 * description: mybatis课程介绍: https://www.bilibili.com/video/BV1mE411X7yp?p=1
 * Github:  https://github.com/mybatis/mybatis-3    7469★
 * 下载地址: https://github.com/mybatis/mybatis-3/releases
 * 官网:     https://mybatis.org/mybatis-3/zh/getting-started.html
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=2
 * 1.Mybatis介绍
 * MyBatis 本是apache的一个开源项目iBatis, 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。2013年11月迁移到Github。
 *      MyBatis是一个优秀的持久层框架，它对jdbc的操作数据库的过程进行封装，使开发者只需要关注 SQL 本身，
 * 	而不需要花费精力去处理例如注册驱动、创建connection、创建statement、手动设置参数、结果集检索等jdbc繁杂的过程代码。
 * Mybatis通过xml或注解的方式将要执行的各种statement（statement、preparedStatemnt、CallableStatement）配置起来，
 * 并通过java对象和statement中的sql进行映射生成最终执行的sql语句，最后由mybatis框架执行sql并将结果映射成java对象并返回。
 * 使用ORM思想实现了结果集的封装, ORM: Object Relational Mapping(对象关系映射)
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=3
 * 1.1.持久层技术解决方案
 * 	JDBC技术:
 * 	    Connection
 * 	    PreparedStatement
 * 	    ResultSet
 * 	Spring的JdbcTemplate:
 * 	    Spring中对jdbc的简单封装
 * 	Apache的DBUtils:
 * 	    它和Spring的 JdbcTemplate 很像, 也是对Jdbc的简单封装
 *
 * 	以上这些都不是框架, JDBC是规范, Spring的JdbcTemplate和Apache的DBUtils都只是工具类
 *
 * 	https://www.bilibili.com/video/BV1mE411X7yp?p=4
 * 	https://www.bilibili.com/video/BV1mE411X7yp?p=5
 * 	mybatis环境搭建-前期准备
 * 	CREATE DATABASE IF NOT EXISTS `mybatis_test`;
 *  USE `mybatis_test`;
 *
 *  CREATE TABLE IF NOT EXISTS `user` (
 *   `id` int(11) NOT NULL AUTO_INCREMENT,
 *   `username` varchar(32) NOT NULL COMMENT '用户名称',
 *   `birthday` datetime DEFAULT NULL COMMENT '生日',
 *   `sex` char(1) DEFAULT NULL COMMENT '性别',
 *   `address` varchar(256) DEFAULT NULL COMMENT '地址',
 *   PRIMARY KEY (`id`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户';
 *
 * INSERT INTO `user` (`id`, `username`, `birthday`, `sex`, `address`) VALUES
 * 	(1, '老王', '2018-02-27 22:52:29', '男', '北京'),
 * 	(2, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 * 	(3, '小二王', '2018-02-27 22:52:29', '女', '北京金燕龙'),
 * 	(4, '传智播客', '2018-02-27 22:52:29', '男', '背景金燕龙'),
 * 	(5, '老王', '2018-02-27 22:52:29', '男', '北京'),
 * 	(6, '小马宝莉', '2018-02-27 22:52:29', '女', '北京修正');
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=6
 * mybatis的环境搭建
 *
 * date       : 2021/5/3 on 22
 * @version 1.0
 */
public class _1Basic {
}
