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

package com.trufleet.service.login.dao;

import static io.dropwizard.testing.FixtureHelpers.fixture;
import static org.fest.assertions.api.Assertions.assertThat;

import com.trufleet.services.jdbi.OrganizationDAO;
import com.trufleet.services.core.Organization;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;
import org.slf4j.*;

import java.io.IOException;
import javax.sql.DataSource;
import org.postgresql.ds.PGPoolingDataSource;

/**
 * Created by Richard Morgan on 11/6/2014.
 */
public class TestOrganizationDAO {

   static Logger logger = LoggerFactory.getLogger(TestOrganizationDAO.class);
   private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

   private static PGPoolingDataSource dataSource;
   private static OrganizationDAO orgdao;

   @BeforeClass
   public static void initialize() throws IOException{

      logger.debug(">>> Initializing Tests. <<<");

       dataSource = new PGPoolingDataSource();
       dataSource.setDataSourceName("TestDataSource");
       dataSource.setServerName("localhost");
       dataSource.setDatabaseName("TruFleetAdminTest");
       dataSource.setUser("postgres");
       dataSource.setPassword("password");
       dataSource.setMaxConnections(10);

       DBI dbi = new DBI(dataSource);
       orgdao = dbi.onDemand(OrganizationDAO.class);

       logger.debug(">>> Leaving Test Initialization <<<");
   }


//  Methods to test
/*  public List<Organization> findAllOrganizations();
    public Organization findOrganizationByName(@Bind("name") String name);
    public Organization findOrganizationbyTenantId(@Bind("tenantid") String tenantid);
    public String insertOrganization(@Bind("name") String name, @Bind("db") String dbURL, @Bind("api") String apiVersion);
    public int updateOrganization(@BindBean Organization org);
    public void removeOrganization(@BindBean Organization org);
    */


    @Test
    public void testInsertNewOrganization() throws IOException{

        Organization org = MAPPER.readValue( fixture("fixtures/incomplete-org.json"), Organization.class);

        logger.debug(">>>> Deserialized Organization object: " + org.toString() +" from JSON. <<<<");

        String returnKey = orgdao.insertOrganization( org.getName(), org.getDatabaseURL(), org.getApiVersion() );

        logger.debug(">>>> insert Organization returned with Tenant ID of: " + returnKey +" <<<<");
        assertThat(returnKey).isNotEmpty();

    }



}
