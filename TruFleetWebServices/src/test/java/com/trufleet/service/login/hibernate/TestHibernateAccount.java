package com.trufleet.service.login.hibernate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.dao.AccountEntityDAO;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;
import org.apache.shiro.authc.Account;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.fest.assertions.api.Assertions.assertThat;

import java.io.File;
import java.io.IOException;

/**
 * Created by Richard on 2/23/2015.
 */
public class TestHibernateAccount extends BaseHibernateTest {

    AccountEntityDAO dao;

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestHibernateAccount.class);

    public TestHibernateAccount(){
        super();
        dao = new AccountEntityDAO(factory);
    }


//    @Test
//    public void PersistNewAccount() throws IOException {
//
//        File file = new File("src/test/resources/fixtures/account-no-id.json");
//
//        AccountEntity accountEntity = MAPPER.readValue(file, AccountEntity.class);
//
//        logger.debug("JSON Read, Account Entity Object Account Name is: " + accountEntity.getName());
//
//        int returnValue = dao.create(accountEntity);
//
//        assertThat(returnValue).isNotNull();
//
//        logger.debug("Account Entity Object Persisted ID returned is: " + returnValue);
//        logger.debug("Attempting Retreival of new Account via ID.");
//
//        AccountEntity retrievedAccount = dao.findById(returnValue);
//
//        assertThat(returnValue).isEqualTo(retrievedAccount.getId());
//
//        logger.debug("Retrieved AccountEntity ID is: " + retrievedAccount.getId() + " and name is: " + retrievedAccount.getName());
//
//
//    }


}
