package com.cargowhale.docker.container.info.resource

import com.cargowhale.docker.client.containers.ContainerState
import com.cargowhale.docker.container.info.model.ContainerDetailsState
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import groovy.transform.TupleConstructor
import org.springframework.hateoas.ResourceSupport

@TupleConstructor(force = true)
@Canonical
class ContainerDetailsStateResource extends ResourceSupport {

    @JsonProperty("status")
    ContainerState state

    @JsonProperty("pid")
    Integer pid

    @JsonProperty("error")
    String error

    @JsonProperty("exitCode")
    Integer exitCode

    @JsonProperty("finishedAt")
    String finishedAt

    ContainerDetailsStateResource(ContainerDetailsState entity) {
        this.state = entity.state
        this.pid = entity.pid
        this.error = entity.error
        this.exitCode = entity.exitCode
        this.finishedAt = entity.finishedAt
    }
}
