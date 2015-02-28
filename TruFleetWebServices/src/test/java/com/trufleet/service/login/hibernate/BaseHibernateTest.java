package com.trufleet.service.login.hibernate;

import junit.framework.TestCase;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richard on 2/23/2015.
 */
public class BaseHibernateTest{

    private static SessionFactory factory;
    private static ServiceRegistry serviceRegistry;

    private static Logger logger = LoggerFactory.getLogger(BaseHibernateTest.class);

    public BaseHibernateTest(){
        initialize();
    }

    private void initialize() {

        Configuration c = new Configuration()
                .setProperty("hibernate.connection.driver_class", "org.postgresql.Driver")
                .setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/trufleettenanttest")
                .setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL9Dialect")
                .setProperty("hibernate.current_session_context_class", "org.hibernate.context.internal.ThreadLocalSessionContext")
                .setProperty("hibernate.connection.username", "postgres")
                .setProperty("hibernate.connection.password", "password");

        serviceRegistry = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();
        factory = c.buildSessionFactory(serviceRegistry);
    }

    protected SessionFactory getSessionFactory(){
        return factory;
    }

}
