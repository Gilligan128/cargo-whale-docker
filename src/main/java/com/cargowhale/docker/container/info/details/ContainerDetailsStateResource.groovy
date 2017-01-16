package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.ContainerState
import com.cargowhale.docker.client.containers.info.inspect.ContainerDetailsState
import groovy.transform.Canonical
import org.springframework.hateoas.ResourceSupport

@Canonical
class ContainerDetailsStateResource extends ResourceSupport {

    ContainerState status
    Integer pid
    String error
    Integer exitCode
    String startedTime
    String finishedTime
    Boolean dead
    Boolean paused
    Boolean restarting
    Boolean running

    ContainerDetailsStateResource(ContainerDetailsState entity) {
        this.status = entity.status
        this.pid = entity.pid
        this.error = entity.error
        this.exitCode = entity.exitCode
        this.startedTime = entity.startedAt
        this.finishedTime = entity.finishedAt
        this.dead = entity.dead
        this.paused = entity.paused
        this.restarting = entity.restarting
        this.running = entity.running
    }
}
