package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.info.inspect.ContainerDetails
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.springframework.hateoas.ResourceSupport

@Canonical
class ContainerDetailsResource extends ResourceSupport {

    ContainerDetailsStateResource state
    ContainerDetailsConfigResource config
    ContainerNetworkSettingsResource networkSettings
    @JsonProperty("id")
    String containerId
    String name
    String imageId

    ContainerDetailsResource(ContainerDetails entity) {
        this.state = new ContainerDetailsStateResource(entity.state)
        this.config = new ContainerDetailsConfigResource(entity.config)
        this.networkSettings = new ContainerNetworkSettingsResource(entity.networkSettings)
        this.containerId = entity.id
        this.name = entity.name
        this.imageId = entity.image
    }
}
