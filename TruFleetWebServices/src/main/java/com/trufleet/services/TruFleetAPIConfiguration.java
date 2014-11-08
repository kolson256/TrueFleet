package com.trufleet.services;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
public class TruFleetAPIConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory adminDatabase = new DataSourceFactory();

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory tenantDatabase = new DataSourceFactory();

    public DataSourceFactory getAdminDatabaseFactory() { return adminDatabase; }

    public DataSourceFactory getTenantDatabaseFactory() { return adminDatabase; }
}
