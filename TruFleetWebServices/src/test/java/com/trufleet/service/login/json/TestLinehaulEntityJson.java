package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.domain.representations.LinehaulEntity;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.time.*;

/**
 * Created by Richard on 2/23/2015.
 */
public class TestLinehaulEntityJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();
    static Logger logger = LoggerFactory.getLogger(TestLinehaulEntityJson.class);


    @Test
    public void writeJSONObjectToFile() throws IOException {
        LinehaulEntity linehaul1 = new LinehaulEntity();

        linehaul1.setNotes("LineHaul1");

        LocalDate shipDate = LocalDate.of(2015, Month.FEBRUARY, 20);
        LocalDateTime deadline = LocalDateTime.of(2015, Month.FEBRUARY, 26, 15, 30);
        LocalDateTime pickupStart = LocalDateTime.of(2015, Month.FEBRUARY, 23, 6, 30);
        LocalDateTime pickupEnd = LocalDateTime.of(2015, Month.FEBRUARY, 23, 9, 30);

        linehaul1.setShipdate( Timestamp.valueOf( shipDate.atStartOfDay()));
        linehaul1.setDeliverydeadline( Timestamp.valueOf(deadline) );
        linehaul1.setPickupstartdate( Timestamp.valueOf(pickupStart)  );
        linehaul1.setPickupenddate(Timestamp.valueOf(pickupEnd) );

        File file = new File("src/test/resources/fixtures", "linehaul-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, linehaul1);
    }


    @Test
    public void readJSONfromFile() throws IOException{

        LinehaulEntity linehaul2 = MAPPER.readValue(new File("src/test/resources/fixtures/linehaul-no-id.json"), LinehaulEntity.class);

        logger.debug("Read Object Value from JSON file: /n" + linehaul2.toString()) ;

        linehaul2.setNotes("Linehaul 2");

        LocalDate shipDate = LocalDate.of(2015, Month.MARCH, 5);
        LocalDateTime deadline = LocalDateTime.of(2015, Month.MARCH, 15, 12, 30);
        LocalDateTime pickupStart = LocalDateTime.of(2015, Month.MARCH, 7, 5,0);
        LocalDateTime pickupEnd = LocalDateTime.of(2015, Month.MARCH, 7, 6, 0);

        File file = new File("src/test/resources/fixtures", "linehaul2-no-id.json");

        PrintWriter writer = new PrintWriter(file, "UTF-8");
        MAPPER.writeValue(writer, linehaul2);
    }

}
