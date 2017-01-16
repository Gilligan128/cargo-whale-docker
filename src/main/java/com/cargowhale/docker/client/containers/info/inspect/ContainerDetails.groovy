package com.cargowhale.docker.client.containers.info.inspect

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import groovy.transform.Canonical

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Canonical
class ContainerDetails {

    ContainerDetailsState state
    ContainerDetailsConfig config
    ContainerNetworkSettings networkSettings
    String id
    String name
    String image
    String path
    String created
    String[] args
    Integer restartCount
    String driver

}
