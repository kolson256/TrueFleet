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

package com.trufleet.services.core.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by Richard Morgan on 10/27/2014.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class Organization{

    @JsonProperty
    @NotNull
    private String name;
    @JsonProperty("tenantid")
    private String tenantID;
    @JsonProperty("databaseurl")
    private String databaseURL;
    @JsonProperty("apiversion")
    private String apiVersion;


    public Organization(){}

    public Organization(String name) {
        this.name = name;
    }

    public Organization(String name, String tenantID, String databaseURL, String apiVersion) {
        this.name = name;
        this.tenantID = tenantID;
        this.databaseURL = databaseURL;
        this.apiVersion = apiVersion;
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
        return databaseURL;
    }

    public void setDatabaseURL(String databaseURL) {
        this.databaseURL = databaseURL;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (apiVersion != null ? !apiVersion.equals(that.apiVersion) : that.apiVersion != null) return false;
        if (databaseURL != null ? !databaseURL.equals(that.databaseURL) : that.databaseURL != null) return false;
        if (!name.equals(that.name)) return false;
        if (tenantID != null ? !tenantID.equals(that.tenantID) : that.tenantID != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (tenantID != null ? tenantID.hashCode() : 0);
        result = 31 * result + (databaseURL != null ? databaseURL.hashCode() : 0);
        result = 31 * result + (apiVersion != null ? apiVersion.hashCode() : 0);
        return result;
    }
}