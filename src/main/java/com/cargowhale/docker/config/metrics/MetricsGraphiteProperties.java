package com.cargowhale.docker.config.metrics;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cargowhale.metrics.graphite", ignoreUnknownFields = false)
public class MetricsGraphiteProperties {

    private Boolean enabled;

    private String host;

    private Integer port;

    private Long period;

    private String prefix;

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(final Boolean enabled) {
        this.enabled = enabled;
    }

    public String getHost() {
        return this.host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public Integer getPort() {
        return this.port;
    }

    public void setPort(final Integer port) {
        this.port = port;
    }

    public Long getPeriod() {
        return this.period;
    }

    public void setPeriod(final Long period) {
        this.period = period;
    }

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
}
