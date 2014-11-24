package com.trufleet.services.resources;

import com.trufleet.services.TruFleetAPIConfiguration;
import com.trufleet.services.auth.InvalidTenantIdException;
import com.trufleet.services.auth.InvalidTokenException;
import com.trufleet.services.core.AppUser;
import com.trufleet.services.core.AuthToken;
import com.trufleet.services.core.Organization;
import com.trufleet.services.jdbi.AppUserDAO;
import com.trufleet.services.jdbi.AuthTokenDAO;
import com.trufleet.services.jdbi.OrganizationDAO;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import org.json.JSONException;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.util.Date;

/**
 * Created by Kyle Olson on 11/6/2014.
 */
public abstract class BaseResource {

    static Logger logger = LoggerFactory.getLogger(BaseResource.class);
    private final TruFleetAPIConfiguration configuration;
    private final Environment environment;

    @Context
    UriInfo uriInfo;

    private DBI adminDb;
    private DBI tenantDb;
    private Organization organization;
    private AppUser appUser;

    public BaseResource(DBI adminDBI, TruFleetAPIConfiguration configuration, Environment environment) throws ClassNotFoundException {
        this.configuration = configuration;
        this.environment = environment;
        this.adminDb = adminDBI;
    }

    protected void buildTenantDb(String tenantId) throws JSONException, ClassNotFoundException, InvalidTenantIdException {

        logger.debug(">>>> Inside buildTenantDB, tenantID given is: " + tenantId);

        final DBIFactory factory = new DBIFactory();
        OrganizationDAO organizationDAO = adminDb.onDemand(OrganizationDAO.class);
        organization = organizationDAO.findOrganizationbyTenantId(tenantId);
        if (organization == null) {
            throw new InvalidTenantIdException("The tenant id is invalid", tenantId);
        }

        DataSourceFactory dataSourceFactory = configuration.getTenantDatabaseFactory();
        dataSourceFactory.setUrl(organization.getDatabaseURL());

        logger.debug(">>>> URL given is : " + dataSourceFactory.getUrl());
        tenantDb = factory.build(environment, dataSourceFactory, "TFTenant");
    }

    protected void buildTenantDb(String tenantId, String authenticationToken)
            throws JSONException, InvalidTenantIdException, ClassNotFoundException, InvalidTokenException {

        buildTenantDb(tenantId);

        AuthTokenDAO authTokenDAO = tenantDb.onDemand(AuthTokenDAO.class);
        AuthToken authToken = authTokenDAO.findAuthToken(authenticationToken);

        if (authToken == null) {
            throw new InvalidTokenException("The token is invalid", authenticationToken);

        }

        if (authToken.getExpirationDate().before(new Date())) {
            throw new InvalidTokenException("This token is expired", authenticationToken);
        }

        AppUserDAO appUserDAO = tenantDb.onDemand(AppUserDAO.class);
        appUser = appUserDAO.findAppUserbyId(authToken.getAppUserId());
    }

    protected DBI getAdminDb() { return adminDb; }
    protected DBI getTenantDb() { return tenantDb; }
    protected Organization getOrganization() { return organization; }
    protected AppUser getAppUser() { return appUser; }
    protected String getVersion() {
        return organization.getApiVersion();
    }

}
