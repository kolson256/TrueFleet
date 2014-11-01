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
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class StormpathConfiguration {

    @JsonProperty
    private boolean enabled = false;

    @NotNull
    @JsonProperty
    private String apiKeyLocation = null;

    @NotNull
    @JsonProperty
    private String applicationRestUrl = null;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getApiKeyLocation() {
        return this.apiKeyLocation;
    }

    public void setApiKeyLocation(final String apiKeyLocation) {
        this.apiKeyLocation = apiKeyLocation;
    }

    public String getApplicationRestUrl() {
        return this.applicationRestUrl;
    }

    public void setApplicationRestUrl(final String applicationRestUrl) {
        this.applicationRestUrl = applicationRestUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return toStringHelper().toString();
    }

    protected Objects.ToStringHelper toStringHelper() {
        return Objects.toStringHelper(this)
                .add("enabled", this.enabled)
                .add("apiKeyLocation", this.apiKeyLocation)
                .add("applicationRestUrl", this.applicationRestUrl);
    }

}
