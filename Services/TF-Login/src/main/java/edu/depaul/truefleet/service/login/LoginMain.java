package edu.depaul.truefleet.service.login;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import com.google.common.base.Optional;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import edu.depaul.truefleet.service.login.resources.LoginResource;
import edu.depaul.truefleet.security.stormpath.shiro.*;
import edu.depaul.truefleet.security.stormpath.*;

import java.util.EnumSet;


public class LoginMain extends Application<LoginConfiguration> {

    private final StormpathShiroBundle stormpathShiroBundle =
            new StormpathShiroBundle<LoginConfiguration>() {
                @Override
                public Optional<StormpathConfiguration> getStormpathConfiguration(final LoginConfiguration configuration) {
                    return Optional.fromNullable(configuration.getStormpathConfiguration());
                }
                @Override
                public Optional<StormpathShiroConfiguration> getStormpathShiroConfiguration(final LoginConfiguration configuration) {
                    return Optional.fromNullable(configuration.getStormpathShiroConfiguration());
                }
            };



    public static void main(String[] args) throws Exception {
        new LoginMain().run(new String[]{"server", System.getProperty("dropwizard.config")});
    }

    @Override
    public String getName() {
        return "LoginMain";
    }

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(Bootstrap<LoginConfiguration> bootstrap) {

        bootstrap.addBundle(this.stormpathShiroBundle);
    }

    @Override
    public void run(LoginConfiguration configuration,
                    Environment environment) {
        final LoginResource loginResource = new LoginResource(  );

        environment.jersey().register(loginResource);

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