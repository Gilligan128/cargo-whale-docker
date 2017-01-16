package com.cargowhale.docker.container.info;

import com.cargowhale.docker.client.containers.info.ContainerInfoClient;
import com.cargowhale.docker.client.containers.info.inspect.ContainerDetails;
import com.cargowhale.docker.client.containers.info.logs.LogFilters;
import com.cargowhale.docker.client.containers.info.stats.ContainerStats;
import com.cargowhale.docker.container.info.model.ContainerLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContainerInfoService {

    private final ContainerInfoClient client;

    @Autowired
    public ContainerInfoService(final ContainerInfoClient client) {
        this.client = client;
    }

    @Deprecated
    ContainerDetails getContainerDetailsById(final String containerId) {
        return this.client.inspectContainer(containerId);
    }

    ContainerLogs getContainerLogsById(final String containerId, final LogFilters logFilters) {
        return this.client.getContainerLogs(containerId, logFilters);
    }

    ContainerStats getContainerStatsById(final String containerId) {
        return this.client.getContainerStats(containerId);
    }
}
