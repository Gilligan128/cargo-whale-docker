package com.cargowhale.docker.container.info.resource

import com.cargowhale.docker.client.containers.info.stats.ContainerStats
import org.springframework.hateoas.Resource

class ContainerStatsResource extends Resource<ContainerStats> {
    ContainerStatsResource(final ContainerStats stats) {
        super(stats)
    }
}
