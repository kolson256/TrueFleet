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

package com.trufleet.services.core;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Organization{

    @JsonProperty
    private String name;
    @JsonProperty
    private String tenantID;

    @JsonProperty
    private String DatabaseURL;
    @JsonProperty
    private String ApiVersion;

    public Organization(String name) {
        this.name = name;
    }

    public Organization(String name, String tenantID, String DatabaseURL, String ApiVersion) {
        this.name = name;
        this.tenantID = tenantID;
        this.DatabaseURL = DatabaseURL;
        this.ApiVersion = ApiVersion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTenantID() {
        return tenantID;
    }

    public void setTenantID(String tenantID) {
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

        if (!name.equals(that.name)) return false;
        if (!tenantID.equals(that.tenantID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + tenantID.hashCode();
        return result;
    }

}
