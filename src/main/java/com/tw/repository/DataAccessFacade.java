package com.tw.repository;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Service;

/**
 * User: imransm, Date: 29 Apr, 2011, Time: 12:20:20 PM
 * Problem:
 */
@Service
@Scope(value = "prototype")
public class DataAccessFacade extends HibernateTemplate {

     @Autowired
    public DataAccessFacade(@Qualifier("gatewaySessionFactory") SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
