package edu.depaul.truefleet.service.login;

/**
 * Created by Rich Morgan on 10/27/2014.
 */

        import edu.depaul.truefleet.security.stormpath.shiro.StormpathShiroConfiguration;
        import edu.depaul.truefleet.security.stormpath.StormpathConfiguration;
        import io.dropwizard.Configuration;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import org.hibernate.validator.constraints.NotEmpty;


        import javax.validation.Valid;

public class LoginConfiguration extends Configuration {

    @Valid
    @NotEmpty
    @JsonProperty("stormpath")
    private StormpathConfiguration stormpathConfiguration = new StormpathConfiguration();

    @Valid
    @NotEmpty
    @JsonProperty("stormpath-shiro")
    private StormpathShiroConfiguration stormpathShiroConfiguration = new StormpathShiroConfiguration();


    public StormpathConfiguration getStormpathConfiguration() {
        return stormpathConfiguration;
    }
    public StormpathShiroConfiguration getStormpathShiroConfiguration() {return stormpathShiroConfiguration; }

}