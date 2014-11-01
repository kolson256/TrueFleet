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

package edu.depaul.truefleet.security.stormpath;

/**
 * Created by Richard Morgan on 10/31/2014.
 */

import java.util.Properties;
import com.google.common.base.Optional;
import com.stormpath.sdk.api.ApiKey;
import com.stormpath.sdk.api.ApiKeys;
import com.stormpath.sdk.client.Client;
import com.stormpath.sdk.client.ClientBuilder;
import com.stormpath.sdk.client.Clients;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class StormpathBundle<T extends Configuration>
        implements ConfiguredBundle<T>, ConfigurationStrategy<T> {

    private static final Logger logger =
            LoggerFactory.getLogger(StormpathBundle.class);

    private Client client;

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final Bootstrap<?> bootstrap) {
        // Do nothing
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run(final T configuration, final Environment environment)
            throws Exception {

        final Optional<StormpathConfiguration> stormpathConfig =
                getStormpathConfiguration(configuration);
        if (stormpathConfig.isPresent()) {
            logger.debug("Stormpath is configured");
            initializeStormpath(stormpathConfig.get());
        } else {
            logger.debug("Stormpath is not configured");
        }
    }

    private void initializeStormpath(final StormpathConfiguration config) {
        if (config.isEnabled()) {
            logger.debug("Stormpath is enabled");
            this.client = buildClient(config);
        } else {
            logger.debug("Stormpath is not enabled");
        }
    }

    private Client buildClient(final StormpathConfiguration config) {

        String path = config.getApiKeyLocation();
        ApiKey apiKey = ApiKeys.builder().setFileLocation(path).build();
        Client client = Clients.builder().setApiKey(apiKey).build();

        return client;
    }

    /**
     * Returns the Stormpath client.
     *
     * @return the Stormpath client
     */
    public Client getClient() {
        return this.client;
    }

}
