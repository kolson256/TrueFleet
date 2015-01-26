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

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.fest.assertions.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trufleet.services.core.Organization;

import io.dropwizard.jackson.Jackson;
import org.junit.Test;

/**
 * Created by Richard Morgan on 11/6/2014.
 */

public class TestOrganizationJson {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    //Test serialization and De-serialization of Organization Object with all fields.
    @Test
    public void serializesToJsonComplete() throws Exception {
        final Organization org1 = new Organization("TestOrganization1", "abcdefghijk-lmnopqrstuvwxyz",
                "jdbc:postgresql://localhost:5432/TruFleetTest", "0.1");

        //serialize Organization object to JSON
        String jacksonJson = MAPPER.writeValueAsString(org1);

        //deserialize JSON fixture to object, then re-serialize.
        String jsonFixture = MAPPER.writeValueAsString(
                MAPPER.readValue( fixture("fixtures/organization-complete.json"), Organization.class));

        assertThat(jacksonJson).isEqualTo(jsonFixture);
    }

    //Test serialization and deserialization of Organization Object without tenantID field.
    @Test
    public void serializesToJsonIncomplete() throws Exception {
        final Organization org3 = new Organization("TestOrganization1");
        org3.setDatabaseURL("jdbc:postgresql://localhost:5432/TruFleetTest");
        org3.setApiVersion("0.1");

        //serialize Organization object to JSON
        String jacksonJson = MAPPER.writeValueAsString(org3);

        //deserialize JSON fixture to object, then re-serialize.
        String jsonFixture = MAPPER.writeValueAsString(
                MAPPER.readValue( fixture("fixtures/organization-incomplete.json"), Organization.class));

        assertThat(jacksonJson).isEqualTo(jsonFixture);
    }

}
