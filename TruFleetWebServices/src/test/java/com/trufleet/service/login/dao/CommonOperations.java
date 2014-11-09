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

/**
 * Created by Richard Morgan on 11/8/2014.
 */

import com.ninja_squad.dbsetup.operation.Operation;

import static com.ninja_squad.dbsetup.Operations.*;

public class CommonOperations {

    public static final Operation DELETE_ALL =
            deleteAllFrom( "userlogin", "organization");

    public static final Operation INSERT_REFERENCE_DATA =
            sequenceOf(
                    insertInto("organization")
                    .columns("name", "tenantid", "databaseurl", "apiversion")
                    .values("TestOrg1", "1234", "jdbc:postgresql://localhost:5432/TruFleetTest", "0.1")
                    .values("TestOrganization2", "2345", "jdbc:postgresql://localhost:5432/TruFleetTest", "0.1")
                    .build() );

}
