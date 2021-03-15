package com.actor.ssmtest._1Test_IOC_DI.service;

import com.actor.ssmtest.dao.CustomerDao;
import com.actor.ssmtest._1Test_IOC_DI.Customer1;

import java.util.List;

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
    }

    @Override
    public List<Customer1> getAll() {
        return null;
    }

//    @Override
//    public List<Customer> getAll(DetachedCriteria dc) {
//    }
}
