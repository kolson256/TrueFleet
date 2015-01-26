package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.core.IntermodalContainer;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Richard on 1/26/2015.
 */
public class TestIntermodalContainerJson {
    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    static Logger logger = LoggerFactory.getLogger(TestUserLoginJson.class);

    @Test
    public void completeIntermodalContainerJsonSerializes() throws Exception{

        IntermodalContainer imc1 = new IntermodalContainer( "ABCD123456");

        String jacksonJson = MAPPER.writeValueAsString(imc1);

        //deserialize JSON fixture to object, then re-serialize.
        String jsonFixture = MAPPER.writeValueAsString(
                MAPPER.readValue( fixture("fixtures/intermodalcontainer-complete.json"), IntermodalContainer.class));

        logger.debug("Serialized Object is: \n" + jacksonJson);
        logger.debug("Fixture Text is: \n" + jsonFixture);

        assertThat(jacksonJson).isEqualTo(jsonFixture);

    }

    @Test
    public void incorrectIntermodalContainerJsonSerializes()throws IOException{

        //Attempt deserialization of JSON fixture that should fail validation
        //Note, Jackson does not throw an exception, this validation will need to be done at the
        //Resource method level in order to properly validate JSON values.
        IntermodalContainer imc2 = MAPPER.readValue( fixture("fixtures/intermodalcontainer-incorrect-1.json"), IntermodalContainer.class);


        String jsonFixture = MAPPER.writeValueAsString(imc2);
        logger.debug("Fixture Text is: \n" + jsonFixture);
        logger.debug("Object Value is: \n" + imc2.getId() );

    }

}
