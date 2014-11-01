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

package edu.depaul.truefleet.security.stormpath.shiro;

/**
 * Created by Richard Morgan on 10/31/2014.
 */

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;
import edu.depaul.truefleet.security.stormpath.StormpathConfiguration;

public class StormpathShiroConfiguration extends StormpathConfiguration {

    @JsonProperty
    private boolean sessionStorageEnabled = false;

    public boolean isSessionStorageEnabled() {
        return this.sessionStorageEnabled;
    }

    public void setSessionStorageEnabled(final boolean sessionStorageEnabled) {
        this.sessionStorageEnabled = sessionStorageEnabled;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Objects.ToStringHelper toStringHelper() {
        return super.toStringHelper()
                .add("sessionStorageEnabled", this.sessionStorageEnabled);
    }

}