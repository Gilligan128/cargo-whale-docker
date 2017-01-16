package com.cargowhale.docker.container.info.model

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY

@EqualsAndHashCode(excludes = "id")
@ToString
class ContainerLogs {

    @JsonProperty(access = WRITE_ONLY)
    final String id
    final String logs

    ContainerLogs(@JsonProperty("Id") final String id,
                  @JsonProperty("Logs") final String logs) {
        this.id = id
        this.logs = logs
    }
}
