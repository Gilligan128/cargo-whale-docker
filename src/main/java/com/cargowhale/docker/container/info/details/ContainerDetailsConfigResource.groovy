package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.info.inspect.ContainerDetailsConfig
import groovy.transform.Canonical

@Canonical
class ContainerDetailsConfigResource {

    String hostname
    String image
    String[] command
    String[] environment

    ContainerDetailsConfigResource(ContainerDetailsConfig entity) {
        this.hostname = entity.hostname
        this.image = entity.image
        this.command = entity.command
        this.environment = entity.environment
    }
}
