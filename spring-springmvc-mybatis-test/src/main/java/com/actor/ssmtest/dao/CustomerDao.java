package com.actor.ssmtest.dao;

import com.actor.ssmtest.domain.Customer;
//import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

public interface CustomerDao {
    //保存客户
    void save(Customer c);

    //查询所有客户
    List<Customer> getAll();

    //根据id获得客户
    Customer getById(Long cust_id);

    //根据条件查询所有客户
//    List<Customer> getAll(DetachedCriteria dc);
}
