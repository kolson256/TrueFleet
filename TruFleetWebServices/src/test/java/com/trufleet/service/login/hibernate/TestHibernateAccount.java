package com.trufleet.service.login.hibernate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.AccountEntityDAO;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.fest.assertions.api.Assertions.fail;

import java.io.File;
import java.io.IOException;

/**
 * Created by Richard on 2/23/2015.
 */
public class TestHibernateAccount extends BaseHibernateTest {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction tx;
    AccountEntityDAO dao;

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestHibernateAccount.class);

    public TestHibernateAccount(){
        super();
    }

    @Before
    public void setup(){
        sessionFactory = getSessionFactory();
        session = sessionFactory.openSession();
        session.beginTransaction();
        dao = new AccountEntityDAO(sessionFactory);

    }


//
//    @Test
//    public void TestPersistNewAccount() throws IOException {
//
//        File file = new File("src/test/resources/fixtures/account-no-id.json");
//
//        try {
//
//            AccountEntity accountEntity = MAPPER.readValue(file, AccountEntity.class);
//            logger.debug("JSON Read, Account Entity Object Account Name is: " + accountEntity.getName());
//
//            session.saveOrUpdate(checkNotNull(accountEntity));
//
//            int returnValue = dao.create(accountEntity);
//
//            assertThat(returnValue).isNotNull();
//
//            logger.debug("Account Entity Object Persisted ID returned is: " + returnValue);
//
//
//        }catch (Exception ex) {
//
//            ex.printStackTrace();
//            if( tx != null){
//                tx.rollback();
//            }
//
//            fail("Exception Thrown");
//        }
//        finally {
//            if (session != null) {
//                session.close();
//            }
//        }
//
//    }


//    @Test
//    public void TestGetAccountByID(){
//
//        Session session;
//        Transaction tx;
//
//        session = factory.openSession();
//        tx = session.beginTransaction();
//
//        try {
//
//            logger.debug("Attempting Retrieval of Account via ID.");
//            AccountEntity retrievedAccount = dao.findById(returnValue);
//
//            assertThat(returnValue).isEqualTo(retrievedAccount.getId());
//
//            logger.debug("Retrieved AccountEntity ID is: " + retrievedAccount.getId() + " and name is: " + retrievedAccount.getName());
//
//            tx.commit();
//
//
//
//        }catch (Exception ex) {
//            ex.printStackTrace();
//            tx.rollback();
//        }
//        finally {session.close();}
//
//    }


}
