package com.piljoong.cacheflow.relay.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cacheflow.relay", ignoreInvalidFields = true)
public class RelayProperties {
    private String target;

    public String getTarget() {
        return this.target;
    }

    public void setTarget(String target) {  this.target = target; }
}
