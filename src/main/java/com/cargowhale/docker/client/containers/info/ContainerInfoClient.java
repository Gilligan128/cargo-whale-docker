package com.cargowhale.docker.client.containers.info;

import com.cargowhale.docker.client.containers.info.list.ContainerListItem;
import com.cargowhale.docker.client.containers.info.top.ContainerTop;
import com.cargowhale.docker.client.core.DockerEndpointBuilder;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.cargowhale.docker.client.core.QueryParameters;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import com.cargowhale.docker.container.info.model.ContainerLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Component
public class ContainerInfoClient {

    private final DockerRestTemplate restTemplate;
    private final DockerEndpointBuilder endpointBuilder;

    @Autowired
    public ContainerInfoClient(final DockerRestTemplate restTemplate, final DockerEndpointBuilder endpointBuilder) {
        this.restTemplate = restTemplate;
        this.endpointBuilder = endpointBuilder;
    }

    public List<ContainerListItem> listContainers() {
        String containersEndpoint = this.endpointBuilder.getListContainersEndpoint();

        ContainerListItem[] containerArray = this.restTemplate.getForObject(containersEndpoint + "?all=1", ContainerListItem[].class);
        return Arrays.asList(containerArray);
    }

    public List<ContainerListItem> listContainers(final QueryParameters filters) {
        String listContainersEndpoint = this.endpointBuilder.getListContainersEndpoint();
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(listContainersEndpoint).queryParams(filters.asQueryParameters());

        ContainerListItem[] containerArray = this.restTemplate.getForObject(builder.toUriString(), ContainerListItem[].class);
        return Arrays.asList(containerArray);
    }

    public ContainerDetails inspectContainer(final String containerId) {
        String containerByIdEndpoint = this.endpointBuilder.getInspectContainerEndpoint(containerId);

        return this.restTemplate.getForObject(containerByIdEndpoint, ContainerDetails.class);
    }

    public ContainerLogs getContainerLogs(final String containerId, final QueryParameters filters) {
        String containerLogEndpoint = this.endpointBuilder.getContainerLogsEndpoint(containerId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(containerLogEndpoint).queryParams(filters.asQueryParameters());

        return new ContainerLogs(containerId, this.restTemplate.getForObject(builder.toUriString(), String.class));
    }

    public ContainerTop getContainerProcesses(final String containerId) {
        String containerByIdEndpoint = this.endpointBuilder.getContainerProcessesEndpoint(containerId);
        return this.restTemplate.getForObject(containerByIdEndpoint, ContainerTop.class);
    }
}