package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IAccountDao;
import com.actor.mybatis_test.dao.IUserDao;
import com.actor.mybatis_test.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * description: Mybatis中的一级缓存和二级缓存
 *
 * date       : 2021/5/8 on 16
 * @version 1.0
 */
public class _8Cache {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
    private IUserDao iUserDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        iUserDao = session.getMapper(IUserDao.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=64
     * 缓存的概念
     * 1.什么是缓存?
     *   存在于内存中的临时数据.
     * 2.为什么使用缓存?
     *   减少和数据库的交互次数, 提高执行效率.
     * 3.什么样的数据能使用缓存, 什么样的数据不能使用?
     *   适用于缓存:
     *      1.经常查询, 并且不经常改变.
     *      2.数据的正确与否, 对最终的结果影响不大.
     *   不适用于缓存:
     *      1.经常改变的数据.
     *      2.数据的正确与否对最终结果影响很大.(商品的库存, 银行的汇率, 股市的牌价)
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=65
     * Mybatis中的一级缓存
     *  指的是Mybatis中SqlSession对象的缓存.
     *  当我们执行查询后, 查询的结果会同时存入SqlSession为我们提供的一块区域中, 该区域的结构是一个Map.
     * 当我们再次查询同样的数据, mybatis会先去SqlSession中查询是否有, 有的话直接拿出来用.
     * 当SqlSession消失时, mybatis的一级缓存也就消失了.
     */
    //测试Mybatis一级缓存
    @Test
    public void testFirstLevelCache() {
        boolean getNewSession = true;
        //第1次查询
        User user1 = iUserDao.findById(5);
        System.out.println(user1);
        System.out.printf("%s@%d\n\n", user1.getClass(), user1.hashCode());

        //如果重新获取SqlSession, 就会查询2次sql
        if (getNewSession) {
            if (true) {
                session.clearCache();//此方法也可以情况缓存
            } else {
                session.close();
                session = factory.openSession();
                iUserDao = session.getMapper(IUserDao.class);
            }
        }

        //如果getNewSession = false, 只发起1次查询, 只打印1次sql日志
        User user2 = iUserDao.findById(5);
        System.out.println(user2);
        System.out.printf("%s@%d\n\n", user2.getClass(), user2.hashCode());
        //打印结果 = !getNewSession
        System.out.println(user1 == user2);
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=66
     * 触发清空一级缓存的情况
     *  一级缓存是SqlSession范围的缓存, 当调用SqlSession的修改(update), 添加(insert), 删除(delete), commit(), close()等方法时,
     * 就会清空一级缓存.
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=67
     * mybatis的二级缓存
     *  指的是mybatis中SqlSessionFactory对象的缓存, 由同一个SqlSessionFactory对象创建的SqlSession共享其缓存.
     * 二级缓存使用步骤:
     *  1.让Mybatis支持二级缓存.(在 SqlMapConfig.xml 中配置)
     *      <configuration>
     *          <settings>
     *              <!-- 配置Mybatis二级缓存, 默认true -->
     *              <setting name="cacheEnabled" value="true"/>
     *
     *  2.让当前的映射文件支持二级缓存.(在 IUserDao.xml 中配置)
     *      <!-- 开启User支持Mybatis二级缓存 -->
     *      <cache/>
     *
     *  3.让当前的操作支持二级缓存.(在 <select 标签中配置: useCache="true")
     *  <select id="findById" parameterType="Integer" resultMap="userMap" useCache="true">
     *     select * from user WHERE id = #{id}
     * </select>
     */
    //测试Mybatis二级缓存
    @Test
    public void testSecondLevelCache() {
        User user1 = iUserDao.findById(5);
        System.out.println(user1);
        System.out.printf("%s@%d\n\n", user1.getClass(), user1.hashCode());

        //一级缓存消失
        session.close();

        SqlSession session = factory.openSession();
        IUserDao iUserDao2 = session.getMapper(IUserDao.class);
        //只打印了1次sql, 只查询了1次
        User user2 = iUserDao2.findById(5);
        System.out.println(user2);
        System.out.printf("%s@%d\n\n", user2.getClass(), user2.hashCode());

        session.close();

        //false, 二级缓存中存放的是数据, 不是对象. 所有是false
        System.out.println(user1 == user2);
    }
}
