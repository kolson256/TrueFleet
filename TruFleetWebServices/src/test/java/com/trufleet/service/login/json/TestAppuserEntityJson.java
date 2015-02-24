package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.AppuserEntity;
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
public class TestAppuserEntityJson{

private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
static Logger logger = LoggerFactory.getLogger(TestAppuserEntityJson.class);


        @Test
        public void writeJSONObjectToFile() throws IOException {
            AppuserEntity appuser1 = new AppuserEntity();

            appuser1.setUsername("appuser1");
            appuser1.setFirstname("AppFirstName");
            appuser1.setLastname("AppLastName");
            appuser1.setRegistrationid("REGID1234");

            File file = new File("src/test/resources/fixtures", "appuser-no-id.json");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            MAPPER.writeValue(writer, appuser1);
        }


        @Test
        public void readJSONfromFile() throws IOException{

            AppuserEntity appuser2 = MAPPER.readValue(new File("src/test/resources/fixtures/appuser-no-id.json"), AppuserEntity.class);

            logger.debug("Read Object Value from JSON file: /n" + appuser2.toString()) ;

            appuser2.setUsername("appuser2");
            appuser2.setFirstname("AppFirstName 2");

            File file = new File("src/test/resources/fixtures", "appuser2-no-id.json");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            MAPPER.writeValue(writer, appuser2);
        }
}
