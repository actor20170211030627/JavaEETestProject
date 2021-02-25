package com.actor.ssmtest.service.impl;

//import org.hibernate.Hibernate;

import com.actor.ssmtest.dao.UserDao;
import com.actor.ssmtest.domain.User;
import com.actor.ssmtest.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao ud;

    public UserDao getUd() {
        return ud;
    }

    public void setUd(UserDao ud) {
        this.ud = ud;
    }

    @Override
    public User login(User user) {
//        //打开事务
//        HibernateUtils.getCurrentSession().beginTransaction();
//        //1.调用Dao根据登陆名称查询User对象
//        User existU = ud.getByUserCode(user.getUser_code());
//        //提交事务
//        HibernateUtils.getCurrentSession().getTransaction().commit();
//
//        if (existU == null) {
//            //获得不到=>抛出异常提示用户名不存在
//            throw new RuntimeException("用户名不存在!");
//        }
//        //2 比对密码是否一致
//        if (!existU.getUser_password().equals(user.getUser_password())) {
//            //不一致=>抛出异常提示密码错误
//            throw new RuntimeException("密码错误!");
//        }
//        //3 将数据库查询的User返回
//        return existU;

        return user;
    }
}
