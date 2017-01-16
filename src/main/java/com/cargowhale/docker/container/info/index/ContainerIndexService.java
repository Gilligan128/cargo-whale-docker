package com.cargowhale.docker.container.info.index;

import com.cargowhale.docker.client.containers.ListContainersParam;
import com.cargowhale.docker.container.info.details.InspectContainerClient;
import com.spotify.docker.client.messages.Container;
import com.spotify.docker.client.messages.ContainerInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ContainerIndexService {

    private final ListContainersClient listContainersClient;
    private final InspectContainerClient inspectContainerClient;
    private final ContainerMapper mapper;

    public ContainerIndexService(final ListContainersClient listContainersClient, final InspectContainerClient inspectContainerClient, final ContainerMapper mapper) {
        this.listContainersClient = listContainersClient;
        this.inspectContainerClient = inspectContainerClient;
        this.mapper = mapper;
    }

    List<ContainerResource> getContainers(final ListContainersParam... filters) {
        List<Container> containers = this.listContainersClient.listContainers(filters);
        Function<Container, ContainerResource> containerResourceMappingFunction = container -> {
            ContainerInfo info = this.inspectContainerClient.inspectContainer(container.id());
            return this.mapper.toResource(container, info);
        };
        return containers.parallelStream().map(containerResourceMappingFunction).collect(Collectors.toList());
    }
}
