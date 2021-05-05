package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IUserDao;
import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.vo.QueryVo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * description: sql语句
 *
 * date       : 2021/5/5 on 15
 * @version 1.0
 */
public class _4If_Where_Foreach_Sql {
    private InputStream in;
    private SqlSession session;
    private IUserDao userDao;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void close() throws IOException {
        //6.释放资源
        session.close();
        in.close();
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=45
     * mybatis中的动态sql语句-<if> 标签
     *
     * https://www.bilibili.com/video/BV1mE411X7yp?p=46
     * mybatis中的动态sql语句-<where> 标签
     */
    @Test
    public void findUserByCondition() {
        User user = new User();
        user.userName = "%王%";
        user.userSex = "男";
//        List<User> users = userDao.findUserByCondition1(user);//<if> 标签, where 1=1 有注入问题
        List<User> users = userDao.findUserByCondition2(user);//<where> 标签
        for (User user1 : users) {
            System.out.println(user1);
        }
    }

    /**
     * https://www.bilibili.com/video/BV1mE411X7yp?p=47
     * mybatis中的动态sql语句-<foreach> 和 <sql> 标签
     * List集合判断:
     * <!-- 可以写成
     *      ids.size > 0,
     *      ids.size() > 0,
     *      !ids.isEmpty(),
     *      !ids.isEmpty,
     *      !ids.isempty -->
     *
     * collection: 要遍历的集合
     * open:       语句开始部分
     * close:      结束部分
     * item:       编辑集合的每个元素
     * separator:  分隔符
     * index:      当前遍历次数, 从0开始
     * <foreach collection="vo.ids" open="and id in(" close=")" item="item" separator="," index="position">
     *
     *
     * <!-- <sql>: 抽取重复的sql语句(了解) -->
     * <sql id="default_query">
     *     select * from user
     *     <!-- <where></where>... -->
     * </sql>
     *
     * <select id="findAll" resultMap="userMap">
     *     <!-- select * from user -->
     *     <include refid="default_query"/>     //引用抽取的sql语句
     * </select>
     */
    @Test
    public void findUserByIds() {
        QueryVo vo = new QueryVo();
        vo.ids = new ArrayList<>();
        vo.ids.add(5);
        vo.ids.add(6);
        List<User> users = userDao.findUserByIds(vo);//<foreach> 标签
        for (User user1 : users) {
            System.out.println(user1);
        }
    }
}
