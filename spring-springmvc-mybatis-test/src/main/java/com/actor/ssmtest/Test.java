package com.actor.ssmtest;

import com.actor.ssmtest.dao.UserDao;
import com.actor.ssmtest.service.UserService;
import com.actor.ssmtest.service.impl.UserServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * description: 描述
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
public class Test {

    public static void main(String[] args) {
        System.out.println(11111);
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//        UserServiceImpl userServiceImpl = (UserServiceImpl) ac.getBean("userService");
        UserServiceImpl userServiceImpl = ac.getBean("userService", UserServiceImpl.class);
        UserDao ud = userServiceImpl.getUd();
        System.out.println(ud);
    }
}
