package com.trufleet.services;

import com.trufleet.services.resources.*;

import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.skife.jdbi.v2.DBI;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

/**
 * Created by Kyle Olson on 11/5/2014.
 */
public class TruFleetAPI extends Application<TruFleetAPIConfiguration> {

    public static void main(String[] args) throws Exception {
        new TruFleetAPI().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public String getName() {
            return "TruFleetAPI";
        }

    @Override
    public void initialize(Bootstrap<TruFleetAPIConfiguration> bootstrap) {

    }

    @Override
    public void run(TruFleetAPIConfiguration configuration, Environment environment) throws Exception {

        final DBIFactory factory = new DBIFactory();
        final DBI adminDBI = factory.build(environment, configuration.getAdminDatabaseFactory(), "TFAdmin");

        environment.jersey().register(new LoginResource(adminDBI, configuration, environment));
        environment.jersey().register(new ProvisionUserResource(adminDBI, configuration, environment));
        environment.jersey().register(new OrganizationResource(adminDBI, configuration, environment));
        environment.jersey().register(new NotificationResource(adminDBI, configuration, environment));
        environment.jersey().register(new GcmRegistrationResource(adminDBI, configuration, environment));
       // environment.jersey().register(new IntermodalContainerResource(adminDBI, configuration, environment));
        environment.jersey().register(new IMTOrderResource(adminDBI, configuration, environment));
        configureCors(environment);
    }

    private void configureCors(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }
}
