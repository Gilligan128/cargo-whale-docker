package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.info.inspect.ContainerNetworkPort
import groovy.transform.Canonical

@Canonical
class ContainerNetworkPortResource {

    String hostIp
    String hostPort

    ContainerNetworkPortResource(ContainerNetworkPort entity) {
        this.hostIp = entity.hostIp
        this.hostPort = entity.hostPort
    }
}
