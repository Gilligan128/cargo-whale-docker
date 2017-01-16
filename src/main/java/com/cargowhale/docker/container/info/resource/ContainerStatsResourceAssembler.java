package com.cargowhale.docker.container.info.resource;

import com.cargowhale.docker.client.containers.info.stats.ContainerStats;
import com.cargowhale.docker.container.info.ContainerDetailsController;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ContainerStatsResourceAssembler extends ResourceAssemblerSupport<ContainerStats, ContainerStatsResource> {

    public ContainerStatsResourceAssembler() {
        super(ContainerDetailsController.class, ContainerStatsResource.class);
    }

    @Override
    public ContainerStatsResource toResource(final ContainerStats entity) {
        ContainerStatsResource resource = createResourceWithId(entity.getId(), entity);

        resource.add(linkTo(methodOn(ContainerDetailsController.class).getContainerById(entity.getId())).withRel("up"));

        return resource;
    }

    @Override
    protected ContainerStatsResource createResourceWithId(final Object id, final ContainerStats entity) {
        ContainerStatsResource resource = new ContainerStatsResource(entity);

        resource.add(linkTo(methodOn(ContainerDetailsController.class).getContainerStatsById(entity.getId())).withSelfRel());

        return resource;
    }
}
