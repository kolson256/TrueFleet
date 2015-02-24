package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.RouteEntity;
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
public class TestRouteEntityJson{

private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
static Logger logger = LoggerFactory.getLogger(TestRouteEntityJson.class);


        @Test
        public void writeJSONObjectToFile() throws IOException {
            RouteEntity route1 = new RouteEntity();

            route1.setNotes("Route Notes 1");

            File file = new File("src/test/resources/fixtures", "route-no-id.json");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            MAPPER.writeValue(writer, route1);
        }


        @Test
        public void readJSONfromFile() throws IOException{

            RouteEntity route2 = MAPPER.readValue(new File("src/test/resources/fixtures/route-no-id.json"), RouteEntity.class);

            logger.debug("Read Object Value from JSON file: /n" + route2.toString()) ;

            route2.setNotes("Route 2");

            File file = new File("src/test/resources/fixtures", "route2-no-id.json");

            PrintWriter writer = new PrintWriter(file, "UTF-8");
            MAPPER.writeValue(writer, route2);
        }
}
