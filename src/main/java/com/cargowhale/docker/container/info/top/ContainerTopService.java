package com.cargowhale.docker.container.info.top;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.containers.info.ContainerInfoClient;
import com.cargowhale.docker.client.containers.info.top.ContainerTop;
import com.cargowhale.docker.client.core.exception.BadContainerStateException;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContainerTopService {

    private final ContainerInfoClient client;
    private final ContainerProcessIndexBuilder processIndexBuilder;

    @Autowired
    public ContainerTopService(final ContainerInfoClient client, final ContainerProcessIndexBuilder processIndexBuilder) {
        this.client = client;
        this.processIndexBuilder = processIndexBuilder;
    }

    public ContainerProcessIndex getContainerProcessesById(final String containerId) {
        ContainerTop dockerIndex = this.client.getContainerProcesses(containerId);
        return this.processIndexBuilder.buildProcessIndex(containerId, dockerIndex);
    }
}
