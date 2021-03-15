package com.actor.ssmtest.service;

import java.util.List;

import com.actor.ssmtest._1Test_IOC_DI.Customer1;
//import org.hibernate.criterion.DetachedCriteria;

public interface CustomerService {
    //保存客户
    void save(Customer1 c);

    //获得所有客户
    List<Customer1> getAll();

    //根据条件查询所有客户
//    List<Customer> getAll(DetachedCriteria dc);
}
