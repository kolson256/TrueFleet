package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.FreightEntity;
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
public class TestFreightEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestFreightEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        FreightEntity freight1 = new FreightEntity();

        freight1.setNotes("Frieght Test 1");
        freight1.setDescription("This is 1 Test");
        freight1.setQuantity(1000);
        freight1.setSeal("Seal ID 1");
        freight1.setWeight(5000);

        File file = new File("src/test/resources/fixtures", "freight-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, freight1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        FreightEntity freight2 = MAPPER.readValue(new File("src/test/resources/fixtures/freight-no-id.json"), FreightEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + freight2.toString()) ;

        freight2.setNotes("Freight Test 2");
        freight2.setDescription("This is 2 Test");
        freight2.setQuantity(3);
        freight2.setSeal("Seal ID 2");
        freight2.setWeight(20000);

        File file = new File("src/test/resources/fixtures", "freight2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, freight2);
    }

}
