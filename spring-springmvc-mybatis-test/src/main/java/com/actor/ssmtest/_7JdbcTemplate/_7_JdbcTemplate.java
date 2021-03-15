package com.actor.ssmtest._7JdbcTemplate;

import com.actor.ssmtest.domain.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * description: {@link JdbcTemplate}(会用就行)
 * https://www.bilibili.com/video/BV1mE411X7yp?p=142
 *  今日课程内容介绍
 * https://www.bilibili.com/video/BV1mE411X7yp?p=143
 *  @see JdbcTemplate 的概述和入门
 *  和'commons-dbutils'一样, 对 JDBC 进行了薄薄的封装.
 *
 *  @see JdbcTemplate 是spring框架中提供的一个对象, 是对原始Jdbc API 对象的简单封装.
 *  spring框架为我们提供了很多的操作模板类.
 *      操作关系型数据库:
 *          @see JdbcTemplate
 *          HibernateTemplate
 *
 *      操作 nosql 数据库的:
 *          RedisTemplate
 *
 *      操作消息队列的:
 *          JmsTemplate
 *
 *      1.添加依赖
 *          <artifactId>spring-jdbc</artifactId>    //spring-jdbc, 包含 JdbcTemplate
 *          <artifactId>spring-tx</artifactId>          //这个和事务相关
 *          <artifactId>mysql-connector-java</artifactId>   //mysql 数据库
 *
 *      2.在代码中获取 {@link JdbcTemplate}, (不推荐)
 *          见: {@link AccountDaoImpl_JdbcTemplate#getJt()}
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=144
 *  JdbcTemplate 在spring的ioc中使用(将 JdbcTemplat 注入spring容器)
 *     <!-- 配置 JdbcTemplate -->
 *     <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
 *         <property name="dataSource" ref="dataSource"/>
 *     </bean>
 *
 *     @Autowired
 *     private JdbcTemplate jt;
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=145
 *  JdbcTemplate 的CRUD操作
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=146
 *  JdbcTemplate 在Dao中的使用
 *
 * https://www.bilibili.com/video/BV1mE411X7yp?p=147
 *  @see org.springframework.jdbc.core.support.JdbcDaoSupport 的使用以及Dao的两种编写方式
 *  1.Xml配置的方式: 见xml配置文件(p=144)
 *    示例:
 *      @see #test_anno()
 *
 *  2.继承{@link JdbcDaoSupport}(感觉麻烦, 不推荐)
 *      2.1.在xml中配置相应dao
 *          <bean id="accountDao" class="com.actor.ssmtest._7JdbcTemplate.AccountDaoImpl_JdbcTemplate">
 *              <property name="dataSource" ref="dataSource"/>
 *          </bean>
 *      2.2.在 accountDao 中调用父类 {@link AccountDaoImpl_JdbcTemplate#getJdbcTemplate()}方法,
 *          获取 JdbcTemplate 对象操作数据库.
 *          示例:
 *              @see #test_extends()
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:_7_JdbcTemplate.xml")
public class _7_JdbcTemplate {

    @Autowired
    private AccountDaoImpl_JdbcTemplate accountDao;

    //测试将 JdbcTemplate 注入spring
    @Test
    public void test_anno() {
        Account one = accountDao.findOne(1);
        System.out.println(one);
    }

    //测试 extends JdbcDaoSupport
    @Test
    public void test_extends() {
        Account accountById = accountDao.findAccountById(1);
        System.out.println(accountById);
    }
}
