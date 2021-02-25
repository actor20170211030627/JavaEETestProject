package com.actor.ssmtest.dao.impl;

//import org.hibernate.Session;

import com.actor.ssmtest.dao.LinkManDao;
import com.actor.ssmtest.domain.LinkMan;

public class LinkManDaoImpl implements LinkManDao {

    @Override
    public void save(LinkMan lm) {
        //1 获得session
//        Session session = HibernateUtils.getCurrentSession();
//        session.save(lm);
    }
}
