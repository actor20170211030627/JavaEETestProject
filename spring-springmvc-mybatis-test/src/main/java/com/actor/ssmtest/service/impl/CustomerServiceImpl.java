package com.actor.ssmtest.service.impl;

import com.actor.ssmtest.dao.CustomerDao;
import com.actor.ssmtest.domain.Customer1;
import com.actor.ssmtest.service.CustomerService;

import java.util.List;

//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import org.hibernate.criterion.DetachedCriteria;

public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public CustomerDao getCustomerDao() {
        return customerDao;
    }

    @Override
    public void save(Customer1 c) {
//        Session session = HibernateUtils.getCurrentSession();
//        //打开事务
//        Transaction tx = session.beginTransaction();
//        //调用Dao保存客户
//        try {
//            customerDao.save(c);
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        }
//        //关闭事务
//        tx.commit();
    }

    @Override
    public List<Customer1> getAll() {
//        Session session = HibernateUtils.getCurrentSession();
//        //打开事务
//        Transaction tx = session.beginTransaction();
//        List<Customer> list = customerDao.getAll();
//        //关闭事务
//        tx.commit();
//        return list;
        return null;
    }

//    @Override
//    public List<Customer> getAll(DetachedCriteria dc) {
//        Session session = HibernateUtils.getCurrentSession();
//        //打开事务
//        Transaction tx = session.beginTransaction();
//        List<Customer> list = customerDao.getAll(dc);
//        //关闭事务
//        tx.commit();
//        return list;
//    }
}