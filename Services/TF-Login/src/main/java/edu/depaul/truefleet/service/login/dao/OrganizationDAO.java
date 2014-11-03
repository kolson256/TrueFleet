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

package edu.depaul.truefleet.service.login.dao;

import edu.depaul.truefleet.service.login.core.Organization;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by Richard Morgan on 11/2/2014.
 */



public interface OrganizationDAO {

    @SqlQuery("select \"Name\" from \"Organization\" where \"Name\" = :name")
    public Organization FindOrganizationByName(@Bind("name") String name);

    @SqlUpdate("insert into \"Organization\" (\"Name\", \"DatabaseURL\", \"APIVersion\") values (:name, :db, :api)")
    void orgInsert(@Bind("name") String name, @Bind("db") String dbURL, @Bind("api") String apiVersion);

}
