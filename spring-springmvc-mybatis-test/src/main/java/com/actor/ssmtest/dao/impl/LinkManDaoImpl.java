package com.actor.ssmtest.dao.impl;

//import org.hibernate.Session;

import com.actor.ssmtest.dao.LinkManDao;
import com.actor.ssmtest.domain.LinkMan;

import java.util.List;

public class LinkManDaoImpl implements LinkManDao {

    @Override
    public void save(LinkMan lm) {
        //1 获得session
//        Session session = HibernateUtils.getCurrentSession();
        //3 执行保存
//        session.save(lm);
    }

    @Override
    public LinkMan getById(Long linkman_id) {
        //1 获得session
//        Session session = HibernateUtils.getCurrentSession();
//        return session.get(LinkMan.class, linkman_id);
        return null;
    }

    @Override
    public List<LinkMan> getAll() {
//        //1 获得session
//        Session session = HibernateUtils.getCurrentSession();
//        //2 创建Criteria对象
//        Criteria c = session.createCriteria(LinkMan.class);
//        return c.list();
        return null;
    }

//    @Override
//    public List<LinkMan> getAll(DetachedCriteria dc) {
//        //1 获得session
//        Session session = HibernateUtils.getCurrentSession();
//        //2 将离线对象关联到session
//        Criteria c = dc.getExecutableCriteria(session);
//        //3 执行查询并返回
//        return c.list();
//    }
}
