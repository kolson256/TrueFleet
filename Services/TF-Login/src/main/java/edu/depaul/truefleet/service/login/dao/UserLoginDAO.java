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

import edu.depaul.truefleet.service.login.core.UserLogin;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

/**
 * Created by Richard Morgan on 11/2/2014.
 */
public interface UserLoginDAO {



    //find User by username
    @SqlQuery("select \"UserName\" from \"UserLogin\" where \"UserName\" = :user")
    public UserLogin FindUserLoginbyUserName(@Bind("user") String username);

    @SqlUpdate("insert into \"UserLogin\" (UserName, Password, OrganizationID) values (:name, :pw, :orgid)")
    void insert(@Bind("name") String name, @Bind("pw") String pw, @Bind("orgid") long orgid);


}
