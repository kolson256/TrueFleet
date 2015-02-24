package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.ChargeEntity;
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
public class TestChargeEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestChargeEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        ChargeEntity charge1 = new ChargeEntity();

        charge1.setDescription("ingate Charge 1");
        charge1.setAmount( 25.00);

        File file = new File("src/test/resources/fixtures", "charge-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, charge1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        ChargeEntity charge2 = MAPPER.readValue(new File("src/test/resources/fixtures/charge-no-id.json"), ChargeEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + charge2.toString()) ;

        charge2.setDescription("PerDiem 2");

        File file = new File("src/test/resources/fixtures", "charge2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, charge2);
    }

}
