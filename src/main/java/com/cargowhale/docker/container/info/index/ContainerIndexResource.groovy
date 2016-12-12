package com.cargowhale.docker.container.info.index

import com.cargowhale.docker.client.containers.ContainerState
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.springframework.hateoas.ResourceSupport

@Canonical
class ContainerIndexResource extends ResourceSupport {

    List<ContainerListItemResource> containers
    Map<ContainerState, Integer> stateMetadata

    ContainerIndexResource(
            @JsonProperty("containers") final List<ContainerListItemResource> containers,
            @JsonProperty("stateMetadata") final Map<ContainerState, Integer> stateMetadata) {
        this.containers = containers
        this.stateMetadata = stateMetadata
    }
}
