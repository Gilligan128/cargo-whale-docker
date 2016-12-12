package com.cargowhale.docker.container.info.index

import com.cargowhale.docker.client.containers.ContainerState
import com.cargowhale.docker.client.containers.info.list.ContainerListItem
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode
import groovy.transform.TupleConstructor
import org.springframework.hateoas.ResourceSupport

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY

@EqualsAndHashCode(excludes = "id")
@TupleConstructor(force = true)
@Canonical
class ContainerListItemResource extends ResourceSupport {

    @JsonProperty("state")
    final ContainerState state

    @JsonProperty(value = "id", access = WRITE_ONLY)
    final String containerId

    @JsonProperty("image")
    final String image

    @JsonProperty("imageId")
    final String imageId

    @JsonProperty("names")
    final String[] names

    @JsonProperty("status")
    final String status

    ContainerListItemResource(ContainerListItem entity) {
        this.state = entity.state
        this.containerId = entity.id
        this.image = entity.image
        this.imageId = entity.imageId
        this.names = entity.names
        this.status = entity.status
    }
}