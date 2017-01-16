package com.cargowhale.docker.container.info.details;

import com.cargowhale.docker.client.core.DockerEndpointBuilder;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InspectContainerClient {

    private final DockerRestTemplate restTemplate;
    private final DockerEndpointBuilder endpointBuilder;

    @Autowired
    public InspectContainerClient(final DockerRestTemplate restTemplate, final DockerEndpointBuilder endpointBuilder) {
        this.restTemplate = restTemplate;
        this.endpointBuilder = endpointBuilder;
    }

    public ContainerInfo inspectContainer(final String containerId) {
        String inspectContainerEndpoint = this.endpointBuilder.getInspectContainerEndpoint(containerId);
        return this.restTemplate.getForObject(inspectContainerEndpoint, ContainerInfo.class);
    }
}
