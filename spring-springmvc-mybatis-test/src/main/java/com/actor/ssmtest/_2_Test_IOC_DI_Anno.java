package com.actor.ssmtest;

import com.actor.ssmtest.dao.CustomerDao;
import com.actor.ssmtest.domain.Customer1;
import com.actor.ssmtest.domain.Customer2;
import com.actor.ssmtest.service.impl.CustomerServiceImpl;
import com.actor.ssmtest.service.impl.UserServiceImpl;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * description: 注解方式注入
 *
 * @author : 李大发
 * date       : 2021/2/25 on 16
 * @version 1.0
 */
public class _2_Test_IOC_DI_Anno {

    public static void main(String[] args) {
        /**
         *
         */
        ApplicationContext ac = new ClassPathXmlApplicationContext("_2_TEST_IOC_DI_Anno.xml");
        CustomerServiceImpl customerService = ac.getBean("customerService", CustomerServiceImpl.class);
        CustomerDao customerDao = customerService.getCustomerDao();
        System.out.printf("ApplicationContext 获取的对象: %s\n", customerDao);

        //构造函数注入示例
        Customer1 customerZs = ac.getBean("customer1", Customer1.class);
        System.out.println(customerZs);

        //set方法注入
        Customer2 customerLs = ac.getBean("customer2", Customer2.class);
        System.out.println(customerLs);
    }
}
