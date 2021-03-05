package com.actor.ssmtest.service.impl;

import com.actor.ssmtest.dao.CustomerDao;
import com.actor.ssmtest.domain.Customer1;
import com.actor.ssmtest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
