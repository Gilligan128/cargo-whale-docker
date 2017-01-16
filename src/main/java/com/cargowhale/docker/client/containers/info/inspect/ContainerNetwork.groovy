package com.cargowhale.docker.client.containers.info.inspect

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class ContainerNetwork {

    @JsonProperty("Aliases")
    List<String> aliases

    @JsonProperty("NetworkID")
    String networkId

    @JsonProperty("EndpointID")
    String endpointId

    @JsonProperty("Gateway")
    String gateway

    @JsonProperty("IPAddress")
    String ipAddress

    @JsonProperty("IPPrefixLen")
    Integer ipPrefixLen

    @JsonProperty("IPv6Gateway")
    String ipV6Gateway

    @JsonProperty("GlobalIPv6Address")
    String globalIPv6Address

    @JsonProperty("GlobalIPv6PrefixLen")
    Integer globalIPv6PrefixLen

    @JsonProperty("MacAddress")
    String macAddress
}
