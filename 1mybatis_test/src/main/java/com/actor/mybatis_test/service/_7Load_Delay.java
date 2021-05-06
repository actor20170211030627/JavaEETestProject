package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IAccountDao;
import com.actor.mybatis_test.dao.IRoleDao;
import com.actor.mybatis_test.domain.AccountUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * description: 描述
 *
 * date       : 2021/5/6 on 22
 * @version 1.0
 */
public class _7Load_Delay {
    private InputStream in;
    private SqlSession session;
    private IRoleDao roleDao;
    private IAccountDao accountDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        roleDao = session.getMapper(IRoleDao.class);
        accountDao = session.getMapper(IAccountDao.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=60
     * 今日课程安排
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=61
     * 延迟加载和立即加载的概念
     * 问题:
     *  1.在一对多中, 一个用户如果有100个账户. 在查询的时候, 要不要把关联的账户查出来?
     *  2.在查询账户时, 要不要把关联的用户查出来.
     * 延迟加载:
     *  真正使用数据时才发起查询, 不用的时候不查询. 按需加载(懒加载)
     * 立即加载:
     *  不管用不用, 只要一调用方法, 马上发起查询.
     *
     * 在对应的四种表关系中:
     *  一对多, 多对多: 通常情况下采用"延迟加载(懒加载)"
     *  多对一, 一对一: 通常情况下采用"立即加载"
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=62
     * mybatis一对一实现延迟加载
     *
     * 1.IAccountDao中配置:
     * <resultMap id="accountUserMapDelay" type="com.actor.mybatis_test.domain.AccountUser">
     *     <id property="id" column="id"/>
     *     <result property="uid" column="uid"/>
     *     <result property="money" column="money"/>
     *     <!-- select属性指定的内容: 延迟加载查询用户的唯一标志 -->
     *     <!-- 延迟加载的时候, column必写 -->
     *     <association property="user" column="uid" javaType="com.actor.mybatis_test.domain.User"
     *                  select="com.actor.mybatis_test.dao.IUserDao.findById"/>
     * </resultMap>
     *
     * 2.sql语句: select * from account
     *
     * 3.在SqlMapConfig.xml中配置<settings, 放在<properties resource=""/>后面, <typeAliases>前面
     * <configuration>
     *     <settings>
     *         <!-- 开启mybatis支持全局延迟加载 -->
     *         <setting name="lazyLoadingEnabled" value="true"/>
     *         <!-- true: 任何方法调用都会加载该对象的所有属性. false: 每个属性会按需加载 -->
     *         <setting name="aggressiveLazyLoading" value="false"/>
     *     </settings>
     * </configuration>
     */
    @Test
    public void findAllAccountDelay() {
        List<AccountUser> accounts = accountDao.findAllAccountDelay();
        for (AccountUser account : accounts) {
            //重写tostring, 如果还不行, 重写get/set(没试, 因为没打印sql日志...)
            System.out.println(account.toString2());
            System.out.println(account.user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=63
     * mybatis一对多实现延迟加载
     */
}
