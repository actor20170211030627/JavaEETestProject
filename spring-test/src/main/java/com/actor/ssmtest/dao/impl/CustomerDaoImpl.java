package com.actor.ssmtest.dao.impl;

import com.actor.ssmtest.dao.CustomerDao;

public class CustomerDaoImpl implements CustomerDao {

    //单例: spring容器创建时会调用
    public void initMethod() {
        System.out.println(getClass().getName() + ": Spring容器初始化");
    }

    public void destroyMethod() {
        System.out.println(getClass().getName() + ": Spring容器销毁");
    }
}
