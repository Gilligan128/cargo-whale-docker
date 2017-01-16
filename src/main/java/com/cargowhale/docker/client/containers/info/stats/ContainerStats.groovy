package com.cargowhale.docker.client.containers.info.stats

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.Canonical
import groovy.transform.EqualsAndHashCode

/**
 * {@see https://github.com/docker/engine-api/blob/release/1.12/types/stats.go}
 */
@EqualsAndHashCode(excludes = "id")
@Canonical
class ContainerStats {

    @JsonIgnore
    String id

    String time
    Map<String, Long> pidStats
    Map<String, ContainerNetworkStats> networkStats
    ContainerMemoryStats memoryStats
    ContainerCpuStats cpuStats
    ContainerCpuStats preCpuStats

    @JsonGetter("time")
    String getTime() {
        return time
    }

    @JsonSetter("read")
    void setTime(String datetime) {
        this.time = datetime
    }

    @JsonGetter("pidStats")
    Map<String, Long> getPidStats() {
        return pidStats
    }

    @JsonSetter("pids_stats")
    void setPidStats(Map<String, Long> pidStats) {
        this.pidStats = pidStats
    }

    @JsonGetter("networkStats")
    Map<String, ContainerNetworkStats> getNetworkStats() {
        return networkStats
    }

    @JsonSetter("networks")
    void setNetworkStats(Map<String, ContainerNetworkStats> networkStats) {
        this.networkStats = networkStats
    }

    @JsonGetter("memoryStats")
    ContainerMemoryStats getMemoryStats() {
        return memoryStats
    }

    @JsonSetter("memory_stats")
    void setMemoryStats(ContainerMemoryStats memoryStats) {
        this.memoryStats = memoryStats
    }

    @JsonGetter("cpuStats")
    ContainerCpuStats getCpuStats() {
        return cpuStats
    }

    @JsonSetter("cpu_stats")
    void setCpuStats(ContainerCpuStats cpuStats) {
        this.cpuStats = cpuStats
    }

    @JsonGetter("preCpuStats")
    ContainerCpuStats getPreCpuStats() {
        return preCpuStats
    }

    @JsonSetter("precpu_stats")
    void setPreCpuStats(ContainerCpuStats preCpuStats) {
        this.preCpuStats = preCpuStats
    }
}
