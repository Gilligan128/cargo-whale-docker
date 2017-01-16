package com.cargowhale.docker.client.containers.info.inspect

import com.cargowhale.docker.client.containers.ContainerState
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import groovy.transform.Canonical

@JsonNaming(PropertyNamingStrategy.UpperCamelCaseStrategy.class)
@Canonical
class ContainerDetailsState {

    ContainerState status
    Integer pid
    String error
    Integer exitCode
    String startedAt
    String finishedAt
    Boolean dead
    Boolean paused
    Boolean restarting
    Boolean running

}
