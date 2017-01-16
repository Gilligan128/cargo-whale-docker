package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.info.inspect.ContainerNetwork
import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@JsonInclude(JsonInclude.Include.NON_NULL)
@Canonical
class ContainerNetworkResource {

    List<String> aliases
    String networkId
    String endpointId
    String gateway
    String ipAddress
    Integer ipPrefixLen
    String ipV6Gateway
    String globalIPv6Address
    Integer globalIPv6PrefixLen
    String macAddress

    ContainerNetworkResource(final ContainerNetwork entity) {
        this.aliases = entity.aliases
        this.networkId = entity.networkId
        this.endpointId = entity.endpointId
        this.gateway = entity.gateway
        this.ipAddress = entity.ipAddress
        this.ipPrefixLen = entity.ipPrefixLen
        this.ipV6Gateway = entity.ipV6Gateway
        this.globalIPv6Address = entity.globalIPv6Address
        this.globalIPv6PrefixLen = entity.globalIPv6PrefixLen
        this.macAddress = entity.macAddress
    }
}
