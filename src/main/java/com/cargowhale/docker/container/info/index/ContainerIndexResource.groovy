package com.cargowhale.docker.container.info.index

import com.cargowhale.docker.client.containers.ContainerState
import groovy.transform.Canonical
import org.springframework.hateoas.Link
import org.springframework.hateoas.Resources

@Canonical
class ContainerIndexResource extends Resources<ContainerResource> {

    Map<ContainerState, Integer> stateMetadata

    ContainerIndexResource() {
    }

    ContainerIndexResource(
        final Iterable<ContainerResource> content,
        final Map<ContainerState, Integer> stateMetadata,
        final Link... links) {
        super(content, links)
        this.stateMetadata = stateMetadata
    }
}
