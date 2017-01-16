package com.cargowhale.docker.container.info.index

import com.cargowhale.docker.client.containers.ContainerState
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import static com.cargowhale.docker.client.containers.ListContainersParam.allContainers

@Service
class ContainerIndexResourceMetadataService {

    private final ListContainersClient client

    @Autowired
    ContainerIndexResourceMetadataService(final ListContainersClient client) {
        this.client = client
    }

    Map<ContainerState, Integer> getStateMetadata() {
        Map<ContainerState, Integer> states = [
            (ContainerState.ALL)       : 0,
            (ContainerState.CREATED)   : 0,
            (ContainerState.RESTARTING): 0,
            (ContainerState.RUNNING)   : 0,
            (ContainerState.PAUSED)    : 0,
            (ContainerState.EXITED)    : 0,
            (ContainerState.DEAD)      : 0
        ]
        def containers = this.client.listContainers(allContainers())

        containers.each { container ->
            states[ContainerState.from(container.state())]++
            states[ContainerState.ALL]++
        }

        return states
    }
}
