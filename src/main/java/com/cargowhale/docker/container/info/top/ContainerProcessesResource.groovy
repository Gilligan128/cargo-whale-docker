package com.cargowhale.docker.container.info.top

import org.springframework.hateoas.Resource

class ContainerProcessesResource extends Resource<ContainerProcessIndex> {
    ContainerProcessesResource(final ContainerProcessIndex process) {
        super(process)
    }
}
