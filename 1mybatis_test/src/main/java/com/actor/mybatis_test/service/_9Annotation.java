package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IAccountDao_Anno;
import com.actor.mybatis_test.dao.IUserDao_Anno;
import com.actor.mybatis_test.domain.AccountUser;
import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.domain.UserAccounts;
import com.actor.mybatis_test.domain.User_Anno;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * description: mybatis注解开发
 *
 * date       : 2021/5/8 on 17
 * @version 1.0
 */
public class _9Annotation {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao_Anno iUserDaoAnno;
    private IAccountDao_Anno iAccountDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig_Anno.xml");//这儿填写注解的配置文件
        //2.创建SqlSessionFactory工厂
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        iUserDaoAnno = session.getMapper(IUserDao_Anno.class);
        iAccountDao = session.getMapper(IAccountDao_Anno.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=68
     * mybatis注解开发的环境搭建
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=69
     * mybatis注解开发测试和使用注意事项
     * 细节: 如果有IUserDao_Anno.xml, 同时使用注解的话, 会报错
     */
    @Test
    public void findAll() {
        List<User_Anno> users = iUserDaoAnno.findAll();
        for (User_Anno user : users) {
            System.out.println(user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=70
     * mybatis注解开发保存和更新功能
     */
    @Test
    public void saveUser() {
        User_Anno user = new User_Anno();
        user.username = "测试注解insert";
        user.birthday = new Date();
        user.sex = "女";
        user.address = "北京市昌平区";
        iUserDaoAnno.saveUser(user);
        //提交事务
        session.commit();
        System.out.println(user);
    }
    @Test
    public void updateUser() {
        User_Anno user = iUserDaoAnno.findById(8);
        user.username = "测试更新";
        user.address = "成都哟";
        iUserDaoAnno.updateUser(user);
        //提交事务
        session.commit();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=71
     * mybatis注解开发CRUD的其他操作
     */
    @Test
    public void deleteUser() {
        iUserDaoAnno.deleteUser(9);
        //提交事务
        session.commit();
    }
    @Test
    public void findByName() {
        List<User_Anno> users = iUserDaoAnno.findByName1("%王%");
        if (false) {//不推荐
            List<User_Anno> users1 = iUserDaoAnno.findByName2("王", 1);
        }
        for (User_Anno user : users) {
            System.out.println(user);
        }
    }
    @Test
    public void findTotal() {
        int total = iUserDaoAnno.findTotal();
        System.out.printf("总条数: %d\n", total);
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=72
     * mybatis注解建立实体类属性和数据库表中列的对应关系
     * 实体 & 数据库 字段对应不上时:
     * @see org.apache.ibatis.annotations.Results: @Results(id = "userMap", value = {}
     * @see org.apache.ibatis.annotations.Result:  @Result(id = true, property = "userId", column = "id")
     * @see org.apache.ibatis.annotations.ResultMap: @ResultMap("userMap")
     */
    @Test
    public void findAllUser() {
        List<User> users = iUserDaoAnno.findAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=73
     * mybatis注解开发一对一的查询配置
     * 每个账户只对应一个User
     */
    @Test
    public void findAllAccountUser() {
        List<AccountUser> accounts = iAccountDao.findAll();
        for (AccountUser account : accounts) {
            System.out.println(account);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=74
     * Mybatis注解开发一对多的查询配置
     */
    @Test
    public void findAllUserAccounts() {
        List<UserAccounts> users = iUserDaoAnno.findAllUserAccounts();
        for (UserAccounts user : users) {
            System.out.println(user);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=75
     * mybatis注解开发使用二级缓存
     *  1.一级缓存默认是开启的
     *  2.开启二级缓存需要配置
     *   2.1.让Mybatis支持二级缓存.(在 SqlMapConfig.xml 中配置)
     *     <configuration>
     *         <settings>
     *             <!-- 配置Mybatis二级缓存, 默认true -->
     *             <setting name="cacheEnabled" value="true"/>
     *   2.2.在 {@link IUserDao_Anno} 类上添加注解: {@link org.apache.ibatis.annotations.CacheNamespace}
     *       @CacheNamespace(blocking = true)
     */
    @Test
    public void testFirstLevelCache() {
        boolean getNewSession = true;
        //第1次查询
        User_Anno user1 = iUserDaoAnno.findById(5);
        System.out.println(user1);
        System.out.printf("%s@%d\n\n", user1.getClass(), user1.hashCode());

        //如果重新获取SqlSession, 就会查询2次sql
        if (getNewSession) {
            if (true) {
                session.clearCache();//此方法也可以情况缓存
            } else {
                session.close();
                session = factory.openSession();
                iUserDaoAnno = session.getMapper(IUserDao_Anno.class);
            }
        }

        //如果getNewSession = false, 只发起1次查询, 只打印1次sql日志
        User_Anno user2 = iUserDaoAnno.findById(5);
        System.out.println(user2);
        System.out.printf("%s@%d\n\n", user2.getClass(), user2.hashCode());
        //打印结果 = !getNewSession
        System.out.println(user1 == user2);
    }

    @Test
    public void testSecondLevelCache() {
        User_Anno user1 = iUserDaoAnno.findById(5);
        System.out.println(user1);
        System.out.printf("%s@%d\n\n", user1.getClass(), user1.hashCode());

        //一级缓存消失
        session.close();

        SqlSession session = factory.openSession();
        IUserDao_Anno iUserDaoAnno2 = session.getMapper(IUserDao_Anno.class);
        //只打印了1次sql, 只查询了1次
        User_Anno user2 = iUserDaoAnno2.findById(5);
        System.out.println(user2);
        System.out.printf("%s@%d\n\n", user2.getClass(), user2.hashCode());

        session.close();

        //false, 二级缓存中存放的是数据, 不是对象. 所有是false
        System.out.println(user1 == user2);
    }
}
