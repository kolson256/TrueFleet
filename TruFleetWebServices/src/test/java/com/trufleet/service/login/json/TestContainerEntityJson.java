package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.ContainerEntity;
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
public class TestContainerEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestContainerEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        ContainerEntity container2 = new ContainerEntity();
        
        container2.setWeight(300);
        container2.setHeight(55);
        container2.setLength(55);
        container2.setVolume(2000);
        container2.setWidth(20);
        container2.setNotes("Container 1");
        container2.setDescription("ABC123456");

        ;

        File file = new File("src/test/resources/fixtures", "container-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, container2);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        ContainerEntity container2 = MAPPER.readValue(new File("src/test/resources/fixtures/container-no-id.json"), ContainerEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + container2.toString()) ;

        container2.setWeight(300);
        container2.setHeight(55);
        container2.setLength(55);
        container2.setVolume(2000);
        container2.setWidth(20);
        container2.setNotes("Container 2");
        container2.setDescription("UMAX432158");

        File file = new File("src/test/resources/fixtures", "container2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, container2);
    }

}
