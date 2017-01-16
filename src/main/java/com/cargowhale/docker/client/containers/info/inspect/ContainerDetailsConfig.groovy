package com.cargowhale.docker.client.containers.info.inspect

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class ContainerDetailsConfig {

    @JsonProperty("Hostname")
    String hostname

    @JsonProperty("Image")
    String image

    @JsonProperty("Cmd")
    String[] command

    @JsonProperty("Env")
    String[] environment
}
