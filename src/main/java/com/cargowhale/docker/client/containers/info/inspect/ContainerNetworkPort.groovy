package com.cargowhale.docker.client.containers.info.inspect

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import groovy.transform.Canonical

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Canonical
class ContainerNetworkPort {

    String hostIp
    String hostPort

}
