package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.ContactEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Richard on 2/23/2015.
 */
public class TestContactEntityJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestContactEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        ContactEntity contact1 = new ContactEntity();

        contact1.setFirstname("First");
        contact1.setLastname("User One");

        File file = new File("src/test/resources/fixtures", "contact-no-id-not-all-fields.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, contact1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        ContactEntity contact2 = MAPPER.readValue(new File("src/test/resources/fixtures/contact-no-id-not-all-fields.json"), ContactEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + contact2.toString()) ;

        contact2.setLastname("User 2");
        contact2.setSuffix("Jr.");
        contact2.setNotes("Second Test Contact");
        contact2.setMailingstreet("12345 Some St.");
        contact2.setMailingcity("Chicago");
        contact2.setMailingstate("IL");
        contact2.setMailingpostalcode("60606");
        contact2.setMailingcountry("USA");
        contact2.setPhone("312-555-1212");
        contact2.setMobilephone("312-555-2121");
        contact2.setFax("312-555-1597");


        File file = new File("src/test/resources/fixtures", "contact2-no-id-all-fields.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, contact2);
    }
}
