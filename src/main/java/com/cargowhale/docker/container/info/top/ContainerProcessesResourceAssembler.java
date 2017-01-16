package com.cargowhale.docker.container.info.top;

import com.cargowhale.docker.container.info.ContainerDetailsController;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class ContainerProcessesResourceAssembler extends ResourceAssemblerSupport<ContainerProcessIndex, ContainerProcessesResource> {

    public ContainerProcessesResourceAssembler() {
        super(ContainerDetailsController.class, ContainerProcessesResource.class);
    }

    @Override
    public ContainerProcessesResource toResource(final ContainerProcessIndex entity) {
        ContainerProcessesResource resource = createResourceWithId(entity.getId(), entity);

        resource.add(linkTo(methodOn(ContainerDetailsController.class).getContainerById(entity.getId())).withRel("up"));

        return resource;
    }

    @Override
    protected ContainerProcessesResource createResourceWithId(final Object id, final ContainerProcessIndex entity) {
        ContainerProcessesResource resource = new ContainerProcessesResource(entity);

        resource.add(ControllerLinkBuilder.linkTo(methodOn(ContainerDetailsController.class).getContainerProcessesById(entity.getId())).withSelfRel());

        return resource;
    }

}
