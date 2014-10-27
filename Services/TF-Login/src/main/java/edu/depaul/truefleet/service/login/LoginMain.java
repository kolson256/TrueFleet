package edu.depaul.truefleet.service.login;

/**
 * Created by Richard Morgan on 10/27/2014.
 */
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import edu.depaul.truefleet.service.login.resources.LoginResource;
import edu.depaul.truefleet.service.login.health.TemplateHealthCheck;

public class LoginMain extends Application<LoginConfiguration> {
    public static void main(String[] args) throws Exception {
        new LoginMain().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<LoginConfiguration> bootstrap) {
        // nothing to do yet
    }

    @Override
    public void run(LoginConfiguration configuration,
                    Environment environment) {
        final LoginResource resource = new LoginResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());

        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);

    }

}