package com.cargowhale.docker.client;

import com.cargowhale.docker.container.ContainerDetails;
import com.cargowhale.docker.container.ContainerInfoVM;
import com.cargowhale.docker.util.JsonConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class ContainerInfoClient {

    private final RestTemplate restTemplate;
    private final DockerEndpointBuilder endpointBuilder;
    private final JsonConverter converter;

    @Autowired
    public ContainerInfoClient(final RestTemplate restTemplate, final DockerEndpointBuilder endpointBuilder, final JsonConverter converter) {
        this.restTemplate = restTemplate;
        this.endpointBuilder = endpointBuilder;
        this.converter = converter;
    }

    public List<ContainerInfoVM> getAllContainers() {
        String containersEndpoint = this.endpointBuilder.getContainersEndpoint();

        ContainerInfoVM[] containerInfoArray = this.restTemplate.getForObject(containersEndpoint + "?all=1", ContainerInfoVM[].class);
        return Arrays.asList(containerInfoArray);
    }

    public List<ContainerInfoVM> getFilteredContainers(DockerContainerFilters filters) {
        String containersEndpoint = this.endpointBuilder.getContainersEndpoint();
        String filterJson = this.converter.toJson(filters);

        ContainerInfoVM[] containerInfoArray = this.restTemplate.getForObject(containersEndpoint + "?filters={filters}", ContainerInfoVM[].class, filterJson);
        return Arrays.asList(containerInfoArray);
    }

    public ContainerDetails getContainerDetailsById(final String containerId) {
        String containerByIdEndpoint = this.endpointBuilder.getContainerByIdEndpoint(containerId);

        return this.restTemplate.getForObject(containerByIdEndpoint, ContainerDetails.class);
    }
}
