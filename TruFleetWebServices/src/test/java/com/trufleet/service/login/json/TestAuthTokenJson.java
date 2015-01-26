package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.core.AuthToken;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.time.Instant;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Richard on 1/26/2015.
 */
public class TestAuthTokenJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    static Logger logger = LoggerFactory.getLogger(TestAuthTokenJson.class);

    @Test
    public void completeAuthTokenJsonSerializes() throws Exception {

        Instant instant = Instant.ofEpochMilli(1422310680934L );
        Timestamp ts1 = Timestamp.from(instant);

        AuthToken au1 = new AuthToken(12345678, "ABCD1234", ts1 );


        String jacksonJson = MAPPER.writeValueAsString(au1);
        logger.debug("Serialized Object is: \n" + jacksonJson);

        //deserialize JSON fixture to object, then re-serialize.
        String jsonFixture = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/authtoken-complete.json"), AuthToken.class));

        logger.debug("Fixture Text is: \n" + jsonFixture);

        assertThat(jacksonJson).isEqualTo(jsonFixture);

    }

    @Test
    public void incompleteAuthTokenJsonSerializes() throws Exception{

        //Attempt deserialization of JSON fixture missing a field ()
        AuthToken au2 = MAPPER.readValue( fixture("fixtures/authtoken-incomplete-1.json"), AuthToken.class);

        String jsonFixture = MAPPER.writeValueAsString(au2);
        logger.debug("Incomplete Fixture Text is: \n" + jsonFixture);

        //assertThat(au2.getFirstName()).isEqualTo("Another");
        //assertThat(au2.getRegistrationId()).isNull();

    }

}
