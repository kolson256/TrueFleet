package edu.depaul.truefleet.service.login;

/**
 * Created by Rich Morgan on 10/27/2014.
 */

        import io.dropwizard.Configuration;
        import com.fasterxml.jackson.annotation.JsonProperty;
        import io.dropwizard.db.DataSourceFactory;
        import org.hibernate.validator.constraints.NotEmpty;

        import javax.validation.Valid;
        import javax.validation.constraints.NotNull;

public class LoginConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory database = new DataSourceFactory();

    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

}