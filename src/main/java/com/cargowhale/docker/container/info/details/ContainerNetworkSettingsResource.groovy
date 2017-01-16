package com.cargowhale.docker.container.info.details

import com.cargowhale.docker.client.containers.info.inspect.ContainerNetworkPort
import com.cargowhale.docker.client.containers.info.inspect.ContainerNetworkSettings
import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.Canonical

@JsonInclude(JsonInclude.Include.NON_NULL)
@Canonical
class ContainerNetworkSettingsResource {

    Map<String, List<ContainerNetworkPortResource>> ports
    Map<String, ContainerNetworkResource> networks

    ContainerNetworkSettingsResource(ContainerNetworkSettings entity) {
        this.ports = buildPortsMap(entity)
        this.networks = buildNetworksMap(entity)
    }

    private Map<String, List<ContainerNetworkPortResource>> buildPortsMap(ContainerNetworkSettings entity) {
        Map<String, List<ContainerNetworkPortResource>> networks = new HashMap<>()
        entity.ports.each { Map.Entry<String, List<ContainerNetworkPort>> entry ->
            networks[entry.key] = entry.value.collect {
                new ContainerNetworkPortResource(it)
            }
        }
        return networks
    }

    private Map<String, ContainerNetworkResource> buildNetworksMap(ContainerNetworkSettings entity) {
        Map<String, ContainerNetworkResource> networks = new HashMap<>()
        entity.networks.each {
            networks[it.key] = new ContainerNetworkResource(it.value)
        }
        return networks
    }
}
