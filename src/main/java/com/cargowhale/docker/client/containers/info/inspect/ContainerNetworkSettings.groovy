package com.cargowhale.docker.client.containers.info.inspect

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class ContainerNetworkSettings {

    @JsonProperty("Bridge")
    String bridge

    @JsonProperty("SandboxID")
    String sandboxId

    @JsonProperty("HairpinMode")
    Boolean hairpinMode

    @JsonProperty("LinkLocalIPv6Address")
    String linkLocalIPv6Address

    @JsonProperty("LinkLocalIPv6PrefixLen")
    Integer linkLocalIPv6PrefixLen

    @JsonProperty("Ports")
    Map<String, List<ContainerNetworkPort>> ports

    @JsonProperty("SandboxKey")
    String sandboxKey

    @JsonProperty("SecondaryIPAddresses")
    Object secondaryIPAddresses

    @JsonProperty("SecondaryIPv6Addresses")
    Object secondaryIPv6Addresses

    @JsonProperty("EndpointID")
    String endpointID

    @JsonProperty("GlobalIPv6Address")
    String globalIPv6Address

    @JsonProperty("GlobalIPv6PrefixLen")
    Integer globalIPv6PrefixLen

    @JsonProperty("IPv6Gateway")
    String ipV6Gateway

    @JsonProperty("Networks")
    Map<String, ContainerNetwork> networks
}
