package com.actor.mybatis_test.service;

import com.actor.mybatis_test.dao.IUserDao;
import com.actor.mybatis_test.dao.IUserDao_Anno;
import com.actor.mybatis_test.domain.User;
import com.actor.mybatis_test.domain.User_Anno;
import org.apache.ibatis.annotations.Select;
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
    private SqlSession session;
    private IUserDao_Anno iUserDaoAnno;
    @Before
    public void init() throws IOException {
        //1.读取配置文件
        in = Resources.getResourceAsStream("SqlMapConfig_Anno.xml");//这儿填写注解的配置文件
        //2.创建SqlSessionFactory工厂
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        iUserDaoAnno = session.getMapper(IUserDao_Anno.class);
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
     * TODO
     */
}
