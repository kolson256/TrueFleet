package com.trufleet.service.login.json;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AccountEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by Richard Morgan on 2/16/2015.
 */
public class TestAccountEntityJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestAccountEntityJson.class);

        
    @Test
    public void writeJSONObjectToFile() throws IOException {
        AccountEntity account1 = new AccountEntity();

        account1.setName("Account One");
        account1.setMailingstreet("123 Some St.");
        account1.setMailingcity("Chicago");
        account1.setMailingstate("IL");
        account1.setMailingpostalcode("60606");
        account1.setMailingcountry("USA");
        account1.setTypes("Broker");
        account1.setNotes("No Notes");
        account1.setPhone("312-555-1212");
        account1.setFax("312-555=2121");

        File file = new File("src/test/resources/fixtures", "account-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, account1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        AccountEntity account2 = MAPPER.readValue(new File("src/test/resources/fixtures/account-no-id.json"), AccountEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + account2.toString()) ;

        account2.setName("Account 2");
        account2.setNotes("This was changed in JSON Test.");
        account2.setMailingstreet("321 Another St.");

        File file = new File("src/test/resources/fixtures", "account2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, account2);
    }
}
