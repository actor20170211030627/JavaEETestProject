package com.actor.ssmtest.dao;

import com.actor.ssmtest.domain.LinkMan;

import java.util.List;

public interface LinkManDao {

    //保存联系人
    void save(LinkMan lm);

    //根据id获得客户
    LinkMan getById(Long cust_id);

    //查询所有客户
    List<LinkMan> getAll();

    //根据条件查询所有客户
//    List<Customer> getAll(DetachedCriteria dc);
}
