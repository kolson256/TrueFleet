package edu.depaul.truefleet.service.login;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import edu.depaul.truefleet.service.login.dao.OrganizationDAO;
import edu.depaul.truefleet.service.login.dao.UserLoginDAO;
import edu.depaul.truefleet.service.login.resources.OrganizationResource;
import edu.depaul.truefleet.service.login.resources.UserRegisterResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import edu.depaul.truefleet.service.login.resources.LoginResource;
import org.skife.jdbi.v2.DBI;

import java.util.EnumSet;


public class LoginMain extends Application<LoginConfiguration> {
    public static void main(String[] args) throws Exception {
        new LoginMain().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public String getName() {
        return "LoginMain";
    }

    @Override
    public void initialize(Bootstrap<LoginConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(LoginConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "TFAdmin");

        final UserLoginDAO dao = jdbi.onDemand(UserLoginDAO.class);
        final OrganizationDAO orgDAO = jdbi.onDemand(OrganizationDAO.class);

        environment.jersey().register(new LoginResource(dao));
        environment.jersey().register(new UserRegisterResource(dao));
        environment.jersey().register(new OrganizationResource( orgDAO ));
        configureCors(environment);
    }

    private void configureCors(Environment environment) {
        Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter("allowedHeaders", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin");
        filter.setInitParameter("allowCredentials", "true");
    }

}