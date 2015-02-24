package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.OrderEntity;
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
public class TestOrderEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestOrderEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        OrderEntity order1 = new OrderEntity();

        order1.setExternalid("1234");
        order1.setNotes("Order 1");
        order1.setOrderid("Another OrderID");
        order1.setReceiptdate( new Timestamp(System.currentTimeMillis()));

        File file = new File("src/test/resources/fixtures", "order-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, order1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        OrderEntity order2 = MAPPER.readValue(new File("src/test/resources/fixtures/order-no-id.json"), OrderEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + order2.toString()) ;

        order2.setNotes("Order 2");
        order2.setOrderid("Order 2 ID");
        order2.setExternalid("External ID for Order 2");
        order2.setReceiptdate(new Timestamp(System.currentTimeMillis()));

        File file = new File("src/test/resources/fixtures", "order2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, order2);
    }

}
