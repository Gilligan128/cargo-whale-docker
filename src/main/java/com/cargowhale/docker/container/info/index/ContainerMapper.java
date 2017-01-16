package com.cargowhale.docker.container.info.index;

import com.spotify.docker.client.messages.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Map;

@Mapper(componentModel = "spring")
public interface ContainerMapper {

    /**
     * PORT BINDING
     */

    @Mappings({
        @Mapping(target = "hostIp", expression = "java(binding.hostIp())"),
        @Mapping(target = "hostPort", expression = "java(binding.hostPort())")
    })
    ContainerResource.NetworkSettings.PortBinding toPortBinding(PortBinding binding);

    List<ContainerResource.NetworkSettings.PortBinding> toPortBindings(List<PortBinding> bindings);

    Map<String, List<ContainerResource.NetworkSettings.PortBinding>> toPortBindingMap(Map<String, List<PortBinding>> map);

    /**
     * ATTACHED NETWORK
     */

    @Mappings({
        @Mapping(target = "networkId", expression = "java(network.networkId())"),
        @Mapping(target = "endpointId", expression = "java(network.endpointId())"),
        @Mapping(target = "gateway", expression = "java(network.gateway())"),
        @Mapping(target = "ipAddress", expression = "java(network.ipAddress())"),
        @Mapping(target = "ipPrefixLen", expression = "java(network.ipPrefixLen())"),
        @Mapping(target = "ipv6Gateway", expression = "java(network.ipv6Gateway())"),
        @Mapping(target = "globalIPv6Address", expression = "java(network.globalIPv6Address())"),
        @Mapping(target = "globalIPv6PrefixLen", expression = "java(network.globalIPv6PrefixLen())"),
        @Mapping(target = "macAddress", expression = "java(network.macAddress())")
    })
    ContainerResource.NetworkSettings.AttachedNetwork toAttachedNetwork(final AttachedNetwork network);

    Map<String, ContainerResource.NetworkSettings.AttachedNetwork> toAttachedNetworkMap(Map<String, AttachedNetwork> map);

    /**
     * NETWORK SETTINGS
     */

    @Mappings({
        @Mapping(target = "ipAddress", expression = "java(settings.ipAddress())"),
        @Mapping(target = "ipPrefixLen", expression = "java(settings.ipPrefixLen())"),
        @Mapping(target = "gateway", expression = "java(settings.gateway())"),
        @Mapping(target = "bridge", expression = "java(settings.bridge())"),
        @Mapping(target = "portMapping", expression = "java(settings.portMapping())"),
        @Mapping(target = "ports", expression = "java(toPortBindingMap(settings.ports()))"),
        @Mapping(target = "macAddress", expression = "java(settings.macAddress())"),
        @Mapping(target = "networks", expression = "java(toAttachedNetworkMap(settings.networks()))")
    })
    ContainerResource.NetworkSettings toNetworkSettings(final NetworkSettings settings);

    /**
     * PORT MAPPING
     */

    ContainerResource.PortMapping toPortMapping(final Container.PortMapping portMapping);

    List<ContainerResource.PortMapping> toPortMappings(final List<Container.PortMapping> portMapping);

    /**
     * CONTAINER MOUNT
     */
    @Mappings({
        @Mapping(target = "source", expression = "java(mount.source())"),
        @Mapping(target = "destination", expression = "java(mount.destination())"),
        @Mapping(target = "mode", expression = "java(mount.mode())"),
        @Mapping(target = "rw", expression = "java(mount.rw())")
    })
    ContainerResource.ContainerMount toContainerMount(final ContainerMount mount);

    List<ContainerResource.ContainerMount> toContainerMounts(final List<ContainerMount> mounts);

    /**
     * CONTAINER RESOURCE
     */

    @Mappings({
        @Mapping(target = "containerId", expression = "java(container.id())"),
        @Mapping(target = "name", expression = "java(info.name())"),
        @Mapping(target = "image", expression = "java(container.image())"),
        @Mapping(target = "imageId", expression = "java(container.imageId())"),
        @Mapping(target = "command", expression = "java(container.command())"),
        @Mapping(target = "created", expression = "java(container.created())"),
        @Mapping(target = "state", expression = "java(container.state())"),
        @Mapping(target = "status", expression = "java(container.status())"),
        @Mapping(target = "ports", expression = "java(toPortMappings(container.ports()))"),
        @Mapping(target = "labels", expression = "java(container.labels())"),
        @Mapping(target = "sizeRw", expression = "java(container.sizeRw())"),
        @Mapping(target = "sizeRootFs", expression = "java(container.sizeRootFs())"),
        @Mapping(target = "networkSettings", expression = "java(toNetworkSettings(container.networkSettings()))"),
        @Mapping(target = "mounts", expression = "java(toContainerMounts(container.mounts()))")
    })
    ContainerResource toResource(final Container container, final ContainerInfo info);
}
