package com.cargowhale.docker.config.metrics;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "metricsdemo.metrics.jvm", ignoreUnknownFields = false)
public class MetricsJvmProperties {

    private Boolean enabled;

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }
}
