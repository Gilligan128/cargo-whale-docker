package com.cargowhale.docker.container.info.index

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical
import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.core.Relation

@Canonical
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(value = "container", collectionRelation = "containers")
class ContainerResource extends ResourceSupport {

    @JsonProperty("id")
    String containerId
    String name
    String image
    String imageId
    String command
    Long created
    String state
    String status
    List<PortMapping> ports
    Map<String, String> labels
    Long sizeRw
    Long sizeRootFs
    NetworkSettings networkSettings
    List<ContainerMount> mounts

    @Canonical
    static class PortMapping {

        int privatePort
        int publicPort
        String type
        String ip
    }

    @Canonical
    static class ContainerMount {

        String source
        String destination
        String mode
        Boolean rw
    }

    @Canonical
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class NetworkSettings {

        String ipAddress
        Integer ipPrefixLen
        String gateway
        String bridge
        Map<String, Map<String, String>> portMapping
        Map<String, List<PortBinding>> ports
        String macAddress
        Map<String, AttachedNetwork> networks

        @Canonical
        static class PortBinding {

            String hostIp
            String hostPort
        }

        @Canonical
        static class AttachedNetwork {

            String networkId
            String endpointId
            String gateway
            String ipAddress
            Integer ipPrefixLen
            String ipv6Gateway
            String globalIPv6Address
            Integer globalIPv6PrefixLen
            String macAddress
        }
    }
}
