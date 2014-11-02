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

package edu.depaul.truefleet.service.login.core;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
public class Organization{

    private String name;
    private long tenantID;
    private String DatabaseURL;
    private String ApiVersion;

    public Organization(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTenantID() {
        return tenantID;
    }

    public void setTenantID(long tenantID) {
        this.tenantID = tenantID;
    }

    public String getDatabaseURL() {
        return DatabaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        DatabaseURL = databaseURL;
    }

    public String getApiVersion() {
        return ApiVersion;
    }

    public void setApiVersion(String apiVersion) {
        ApiVersion = apiVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (tenantID != that.tenantID) return false;
        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (int) (tenantID ^ (tenantID >>> 32));
        return result;
    }
}
