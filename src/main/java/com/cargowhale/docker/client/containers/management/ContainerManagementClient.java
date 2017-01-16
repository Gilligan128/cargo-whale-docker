package com.cargowhale.docker.client.containers.management;

import com.cargowhale.docker.client.containers.management.state.ContainerChangeState;
import com.cargowhale.docker.client.core.DockerEndpointBuilder;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContainerManagementClient {

    private final DockerRestTemplate restTemplate;
    private final DockerEndpointBuilder endpointBuilder;

    @Autowired
    public ContainerManagementClient(final DockerRestTemplate restTemplate, final DockerEndpointBuilder endpointBuilder) {
        this.restTemplate = restTemplate;
        this.endpointBuilder = endpointBuilder;
    }

    public String changeContainerState(final String name, final ContainerChangeState state) {
        String containersEndpoint = this.endpointBuilder.getContainerChangeStateEndpoint(name, state);

        this.restTemplate.postForObject(containersEndpoint + "?t=5", null, String.class, state);
        return name;
    }
}
