package com.trufleet.service.login.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richard on 2/23/2015.
 */
public class BaseHibernateTest {

    protected SessionFactory factory;
    static Logger logger = LoggerFactory.getLogger(BaseHibernateTest.class);

    public BaseHibernateTest(){
        initialize();
    }

    private void initialize() {

//        Configuration c = new Configuration()
//                .setProperty("connection.driver_class", "org.postgresql.Driver")
//                .setProperty("connection.url", "jdbc:postgresql://localhost:5432/trufleettenanttest")
//                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
//                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
//                .setProperty("connection.username", "postgres")
//                .setProperty("conneciton.password", "password");
//
//        factory = c.buildSessionFactory();
    }


}
