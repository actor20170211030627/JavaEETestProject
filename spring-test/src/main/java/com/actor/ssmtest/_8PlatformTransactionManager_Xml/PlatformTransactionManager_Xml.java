package com.actor.ssmtest._8PlatformTransactionManager_Xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Propagation;

/**
 * https://www.bilibili.com/video/BV1mE411X7yp?p=151
 *  spring中事务控制的一组API
 *  (事务控制 基于APO)
 *
 * 1.{@link PlatformTransactionManager}
 *  此接口是spring的事务管理器, 它里面提供了我们常用的操作事务的方法, 如下:
 *      1.获取事务状态信息
 *      @see PlatformTransactionManager#getTransaction(TransactionDefinition)
 *      2.提交事务
 *      @see PlatformTransactionManager#commit(TransactionStatus)
 *      3.回滚事务
 *      @see PlatformTransactionManager#rollback(TransactionStatus)
 *
 * 它的实现类:
 *      1.使用Spring JDBC 或 iBatis 进行持久化数据时使用.
 *      @see DataSourceTransactionManager
 *      2.使用 Hibernate 版本进行持久化数据时使用.
 *      @see org.springframework.orm.hibernate5.HibernateTransactionManager
 *
 * ###########################################################
 * 2.{@link TransactionDefinition}
 *  事务的定义信息对象, 里面如下方法:
 *      1.获取事务对象名称
 *      @see TransactionDefinition#getName()
 *      2.获取事务隔离级别(4个级别, spring默认使用数据库的级别)
 *      @see TransactionDefinition#getIsolationLevel()
 *      3.获取事务传播行为(什么情况下有(增删改)/没有(查)事务)
 *      @see TransactionDefinition#getPropagationBehavior()
 *      4.获取事务超时时间(提交/回滚多长时间过期, 可配置)
 *      @see TransactionDefinition#getTimeout()
 *      5.获取事务是否只读(只读(查询)/读写)
 *      @see TransactionDefinition#isReadOnly()
 *
 *  2.隔离级别, 反映事务提交并发访问时的处理态度
 *      1.默认级别, 归属下列某一种
 *      @see TransactionDefinition#ISOLATION_DEFAULT
 *      2.可以读取未提交数据
 *      @see TransactionDefinition#ISOLATION_READ_UNCOMMITTED
 *      3.只能读取已提交数据, 解决胀读问题(Oracle默认级别)
 *      @see TransactionDefinition#ISOLATION_READ_COMMITTED
 *      4.是否读取其它事务提交修改后的数据, 解决不可重复度问题(MySQL默认级别)
 *      @see TransactionDefinition#ISOLATION_REPEATABLE_READ
 *      5.是否读取其它事务提交添加后的数据, 解决幻影读问题
 *      @see TransactionDefinition#ISOLATION_SERIALIZABLE
 *
 * 3.事务传播行为: @Transactional(propagation = Propagation.REQUIRED)
 *      1.如果当前没有事务，就创建一个新事务，如果当前存在事务，就加入该事务，(默认值),(required需要，没有新建，有加入)
 *      @see Propagation#REQUIRED
 *      2.支持当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就以非事务执行。(查询)（supports支持，有则加入，没有就不管了，没有事务）
 *      @see Propagation#SUPPORTS
 *      3.使用当前事务，如果当前存在事务，就加入该事务，如果当前不存在事务，就抛出异常。（mandatory强制性，有则加入，没有异常）
 *      @see Propagation#MANDATORY
 *      4.创建新事务，无论当前存不存在事务，都创建新事务。（requires_new需要新的，不管有没有，直接创建新事务）
 *      @see Propagation#REQUIRES_NEW
 *      5.以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。（not supported不支持事务，存在就挂起）
 *      @see Propagation#NOT_SUPPORTED
 *      6.以非事务方式执行，如果当前存在事务，则抛出异常。（never不支持事务，存在就异常）
 *      @see Propagation#NEVER
 *      7.如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则按 REQUIRED 属性执行。（nested存在就在嵌套的执行，没有就找是否存在外面的事务，有则加入，没有则新建）
 *      @see Propagation#NESTED
 *
 * 4.超时时间
 *      默认值-1, 没有超时限制. 如果有, 以秒为单位进行设置.
 *
 * 5.是否是只读事务
 *      建议'查询"时设置为只读.
 *
 * 6.{@link TransactionStatus}
 *  此接口提供的是某个时间点上事务对象的具体的运行状态信息, 里面如下方法:
 *      @see TransactionStatus#flush()           //刷新事务
 *      @see TransactionStatus#hasSavepoint()    //事务是否存在存储点(事务按步提交)
 *      @see TransactionStatus#isCompleted()     //事务是否完成
 *      @see TransactionStatus#isNewTransaction()//事务是否为新的事务
 *      @see TransactionStatus#isRollbackOnly()  //事务是否回滚
 *      @see TransactionStatus#setRollbackOnly() //设置事务回滚
 *
 *
 * ###########################################################
 * https://www.bilibili.com/video/BV1mE411X7yp?p=152
 *  Spring事务控制的代码准备
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=153
 *  Spring基于XML的声明式事务控制-配置步骤
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:_8PlatformTransactionManager.xml")
public class PlatformTransactionManager_Xml {

    @Autowired
    private AccountDaoImpl accountDao;

    @Test
    public void findAll() {
        accountDao.findAll(0F);//1000F
    }

    /**
     * 测试转账的事务: 异常
     */
    @Test
    public void transferMoney() {
        accountDao.transferMoney("aaa", "bbb", 100F, true);//true: 制造异常
    }
}

























