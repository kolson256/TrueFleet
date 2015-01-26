/*
 * Copyright (c) 2014. Richard Morgan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.trufleet.service.login.json;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.trufleet.services.core.UserLogin;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

/**
 * Created by Richard Morgan on 11/6/2014.
 */
public class TestUserLoginJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    static Logger logger = LoggerFactory.getLogger(TestUserLoginJson.class);

    @Test
    public void completeUserLoginJsonSerializes() throws Exception{

        UserLogin ul1 = new UserLogin( "testUser","password", "12345678");

        String jacksonJson = MAPPER.writeValueAsString(ul1);

        //deserialize JSON fixture to object, then re-serialize.
        String jsonFixture = MAPPER.writeValueAsString(
                MAPPER.readValue( fixture("fixtures/userlogin-complete.json"), UserLogin.class));

        logger.debug("Serialized Object is: \n" + jacksonJson);
        logger.debug("Fixture Text is: \n" + jsonFixture);

        assertThat(jacksonJson).isEqualTo(jsonFixture);

    }

    @Test
    public void incompleteUserLoginJsonSerializes() throws Exception{

        //Attempt deserialization of JSON fixture missing a field (TenantID)
        UserLogin au2 = MAPPER.readValue( fixture("fixtures/userlogin-incomplete-1.json"), UserLogin.class);

        String jsonFixture = MAPPER.writeValueAsString(au2);
        logger.debug("Fixture Text is: \n" + jsonFixture);

        assertThat(au2.getUsername()).isEqualTo("testUser");
        assertThat(au2.getTenantId()).isNull();

    }


}
