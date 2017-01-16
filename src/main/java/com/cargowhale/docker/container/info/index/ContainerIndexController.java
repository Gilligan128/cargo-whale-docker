package com.cargowhale.docker.container.info.index;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.containers.ListContainersParam;
import com.cargowhale.docker.container.ContainerEnumConverter;
import com.cargowhale.docker.exception.CargoWhaleErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.cargowhale.docker.client.containers.ListContainersParam.allContainers;
import static java.util.Arrays.stream;

@RestController
@RequestMapping("/api/containers")
public class ContainerIndexController {

    private final ContainerIndexService service;
    private final ContainerIndexResourceAssembler resourceAssembler;

    @Autowired
    public ContainerIndexController(final ContainerIndexService service, final ContainerIndexResourceAssembler resourceAssembler) {
        this.service = service;
        this.resourceAssembler = resourceAssembler;
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder) {
        binder.registerCustomEditor(ContainerState.class, new ContainerEnumConverter());
    }

    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<CargoWhaleErrorMessage> handleBadFilter(final HttpServletRequest request, final BindException ex) {
        CargoWhaleErrorMessage errorMessage = new CargoWhaleErrorMessage(request.getRequestURI(), "Bad Filter", ex.getClass().toString());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerIndexResource listContainers() {
        List<ContainerResource> containerResources = this.service.getContainers(allContainers());

        return this.resourceAssembler.toResource(containerResources);
    }

    @RequestMapping(method = RequestMethod.GET,
        params = "state",
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerIndexResource listContainers(@RequestParam final ContainerState... state) {
        ListContainersParam[] params = stream(state)
            .map(ListContainersParam::state)
            .toArray(ListContainersParam[]::new);

        List<ContainerResource> containerResources = this.service.getContainers(params);

        return this.resourceAssembler.toResource(containerResources);
    }
}
