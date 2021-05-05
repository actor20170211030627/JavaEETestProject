package com.actor.mybatis_test.service;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

/**
 * description: 连接池 & 事务
 *
 * date       : 2021/5/4 on 23
 * @version 1.0
 */
public class _3ConnectionPool_Transaction {
    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=39
     * 今日课程内容介绍
     * https://www.bilibili.com/video/BV1mE411X7yp?p=40
     * 1.连接池介绍
     *      在实际开发中都会使用连接池, 可以减少获取连接所消耗的时间.
     *      连接池就是用于存储连接的一个容器. 容器是一个线程安全的集合, 不能2个线程拿到同一个连接.
     *      该集合还必须实现队列的特性: 先进先出
     * 2.mybatis中的连接池
     *      3中配置方式(resource/SqlMapConfig.xml 中的 <DataSource):
     *      <DataSource type属性:
     *          POOLED  : 采用传统的javax.sql.DataSource规范中的连接池. mybatis中有针对规范的实现
     *          UNPOOLED: 采用传统的获取连接的方式, 虽然也实现了javax.sql.DataSource接口, 但是并没有使用"池"的思想(每次用都重新获取一个连接)
     *          JNDI    : 采用服务器提供的JNDI技术实现, 来获取DataSource对象, 不同的服务器所能拿到的DataSource是不一样的.
     *                    注意: 如果不是web或maven的war工程, 是不能使用的.
     *                          我们课程中使用的是tomcat服务器, 采用的连接池是dbcp连接池.
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=42
     * mybatis中使用unpooled配置连接池的原理分析
     * @see PooledDataSource#getConnection()    //POOLED
     * @see UnpooledDataSource#getConnection()  //UNPOOLED
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=43
     * mybatis中使用pooled配置连接池的原理分析
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=44
     * mybatis中的事务原理和自动提交设置
     *  1.什么是事务
     *  2.事务的四大特性ACID
     *  3.不考虑隔离性会产生的3个问题
     *  4.解决办法: 四种隔离级别
     *
     * mybatis中的事务: 通过sqlsession对象的commit()和rollback()实现事务的提交和回滚
     * @see SqlSessionFactory#openSession(boolean)  //是否自动提交事务, 默认false
     * @see SqlSession#commit()                     //如果打开自动提交, 就不用再手动提交
     */
}
