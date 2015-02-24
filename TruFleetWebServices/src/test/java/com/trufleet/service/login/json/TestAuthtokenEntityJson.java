package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AuthtokenEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

/**
 * Created by Richard on 2/23/2015.
 */
public class TestAuthtokenEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestAuthtokenEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        AuthtokenEntity authtoken1 = new AuthtokenEntity();

        authtoken1.setToken("12345");
        authtoken1.setAppuserid(2);
        authtoken1.setExpirationdate( new Timestamp(System.currentTimeMillis()));

        File file = new File("src/test/resources/fixtures", "authtoken-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, authtoken1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        AuthtokenEntity authtoken2 = MAPPER.readValue(new File("src/test/resources/fixtures/authtoken-no-id.json"), AuthtokenEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + authtoken2.toString()) ;

        authtoken2.setToken("12345");
        authtoken2.setAppuserid(2);
        authtoken2.setExpirationdate(new Timestamp(System.currentTimeMillis()));

        File file = new File("src/test/resources/fixtures", "authtoken2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, authtoken2);
    }

}
