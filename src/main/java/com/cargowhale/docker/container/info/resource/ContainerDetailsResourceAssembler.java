package com.cargowhale.docker.container.info.resource;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.containers.info.logs.LogFilters;
import com.cargowhale.docker.container.info.ContainerDetailsController;
import com.cargowhale.docker.container.info.index.ContainerIndexController;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ContainerDetailsResourceAssembler extends ResourceAssemblerSupport<ContainerDetails, ContainerDetailsResource> {

    public ContainerDetailsResourceAssembler() {
        super(ContainerDetailsController.class, ContainerDetailsResource.class);
    }

    @Override
    public ContainerDetailsResource toResource(final ContainerDetails entity) {
        ContainerDetailsResource resource = createResourceWithId(entity.getId(), entity);

        resource.add(linkTo(methodOn(ContainerIndexController.class).listContainers()).withRel("up"));
        resource.add(linkTo(methodOn(ContainerDetailsController.class).getContainerLogsById(entity.getId(), new LogFilters())).withRel("logs"));

        if (entity.getContainerState() == ContainerState.RUNNING) {
            resource.add(linkTo(methodOn(ContainerDetailsController.class).getContainerProcessesById(entity.getId())).withRel("top"));
        }

        return resource;
    }

    @Override
    protected ContainerDetailsResource instantiateResource(final ContainerDetails entity) {
        return new ContainerDetailsResource(entity);
    }
}
