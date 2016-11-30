package com.cargowhale.docker.container.info;

import com.cargowhale.docker.container.ContainerEnumConverter;
import com.cargowhale.docker.container.ContainerState;
import com.cargowhale.docker.container.LogFilters;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import com.cargowhale.docker.container.info.model.ContainerLogs;
import com.cargowhale.docker.container.info.model.ContainerProcesses;
import com.cargowhale.docker.container.info.resource.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/containers")
public class ContainerDetailsController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(ContainerState.class, new ContainerEnumConverter());
    }

    private final ContainerInfoService service;
    private final ContainerDetailsResourceAssembler detailsResourceAssembler;
    private final ContainerLogsResourceAssembler logsResourceAssembler;
    private final ContainerProcessesResourceAssembler processesResourceAssembler;

    @Autowired
    public ContainerDetailsController(final ContainerInfoService service, final ContainerDetailsResourceAssembler detailsResourceAssembler, final ContainerLogsResourceAssembler logsResourceAssembler, final ContainerProcessesResourceAssembler processesResourceAssembler) {
        this.service = service;
        this.detailsResourceAssembler = detailsResourceAssembler;
        this.logsResourceAssembler = logsResourceAssembler;
        this.processesResourceAssembler = processesResourceAssembler;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ContainerDetailsResource getContainerById(@PathVariable String id) {
        ContainerDetails containerDetails = this.service.getContainerDetailsById(id);
        return this.detailsResourceAssembler.toResource(containerDetails);
    }

    @RequestMapping(value = "/{id}/logs",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ContainerLogsResource getContainerLogsById(@PathVariable String id, LogFilters logFilters) {
        ContainerLogs containerLogs = this.service.getContainerLogsById(id, logFilters);
        return this.logsResourceAssembler.toResource(containerLogs);
    }

    @RequestMapping(value = "/{id}/top",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ContainerProcessesResource getContainerProcessesById(@PathVariable String id) {
        ContainerProcesses containerProcesses = this.service.getContainerProcessesById(id);
        return this.processesResourceAssembler.toResource(containerProcesses);
    }
}
