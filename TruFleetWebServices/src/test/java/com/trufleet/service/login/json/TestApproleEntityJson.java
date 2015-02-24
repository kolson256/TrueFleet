package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.ApproleEntity;
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
public class TestApproleEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestApproleEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        ApproleEntity approle1 = new ApproleEntity();

        approle1.setName("RoleName1");

        File file = new File("src/test/resources/fixtures", "approle-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, approle1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        ApproleEntity approle2 = MAPPER.readValue(new File("src/test/resources/fixtures/approle-no-id.json"), ApproleEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + approle2.toString()) ;

        approle2.setName("Approle 2");

        File file = new File("src/test/resources/fixtures", "approle2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, approle2);
    }
    
}
