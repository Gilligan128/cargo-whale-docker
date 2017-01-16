package com.cargowhale.docker.config.docker;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "cargowhale.docker", ignoreUnknownFields = false)
public class DockerProperties {

    private String uri;

    public String getUri() {
        return this.uri;
    }

    public void setUri(final String uri) {
        this.uri = uri;
    }
}
