package com.cargowhale.docker.container.info;

import com.cargowhale.docker.client.containers.info.inspect.ContainerDetails;
import com.cargowhale.docker.client.containers.info.logs.LogFilters;
import com.cargowhale.docker.client.containers.info.stats.ContainerStats;
import com.cargowhale.docker.container.info.details.ContainerDetailsResource;
import com.cargowhale.docker.container.info.details.ContainerDetailsResourceAssembler;
import com.cargowhale.docker.container.info.model.ContainerLogs;
import com.cargowhale.docker.container.info.resource.ContainerLogsResource;
import com.cargowhale.docker.container.info.resource.ContainerLogsResourceAssembler;
import com.cargowhale.docker.container.info.resource.ContainerStatsResource;
import com.cargowhale.docker.container.info.resource.ContainerStatsResourceAssembler;
import com.cargowhale.docker.container.info.top.ContainerProcessIndex;
import com.cargowhale.docker.container.info.top.ContainerProcessesResource;
import com.cargowhale.docker.container.info.top.ContainerProcessesResourceAssembler;
import com.cargowhale.docker.container.info.top.ContainerTopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/containers")
public class ContainerDetailsController {

    private final ContainerInfoService infoService;
    private final ContainerTopService topService;
    private final ContainerDetailsResourceAssembler detailsResourceAssembler;
    private final ContainerLogsResourceAssembler logsResourceAssembler;
    private final ContainerStatsResourceAssembler statsResourceAssembler;
    private final ContainerProcessesResourceAssembler processesResourceAssembler;

    @Autowired
    public ContainerDetailsController(final ContainerInfoService infoService, final ContainerTopService topService, final ContainerDetailsResourceAssembler detailsResourceAssembler, final ContainerLogsResourceAssembler logsResourceAssembler, final ContainerStatsResourceAssembler statsResourceAssembler, final ContainerProcessesResourceAssembler processesResourceAssembler) {
        this.infoService = infoService;
        this.topService = topService;
        this.detailsResourceAssembler = detailsResourceAssembler;
        this.logsResourceAssembler = logsResourceAssembler;
        this.statsResourceAssembler = statsResourceAssembler;
        this.processesResourceAssembler = processesResourceAssembler;
    }

    @RequestMapping(value = "/{id}",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerDetailsResource getContainerById(@PathVariable final String id) {
        ContainerDetails containerDetails = this.infoService.getContainerDetailsById(id);
        return this.detailsResourceAssembler.toResource(containerDetails);
    }

    @RequestMapping(value = "/{id}/logs",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerLogsResource getContainerLogsById(@PathVariable final String id, final LogFilters logFilters) {
        ContainerLogs containerLogs = this.infoService.getContainerLogsById(id, logFilters);
        return this.logsResourceAssembler.toResource(containerLogs);
    }

    @RequestMapping(value = "/{id}/top",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerProcessesResource getContainerProcessesById(@PathVariable final String id) {
        ContainerProcessIndex containerProcessIndex = this.topService.getContainerProcessesById(id);
        return this.processesResourceAssembler.toResource(containerProcessIndex);
    }

    @RequestMapping(value = "/{id}/stats",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public ContainerStatsResource getContainerStatsById(@PathVariable final String id) {
        ContainerStats containerStats = this.infoService.getContainerStatsById(id);
        containerStats.setId(id);
        return this.statsResourceAssembler.toResource(containerStats);
    }

}
