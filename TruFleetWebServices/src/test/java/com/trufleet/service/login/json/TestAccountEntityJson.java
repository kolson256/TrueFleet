package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class TestAccountEntityJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestAccountEntityJson.class);


    @Test
    public void writeJSONFixtures(){
        AccountEntity account = new AccountEntity();
    }

}
