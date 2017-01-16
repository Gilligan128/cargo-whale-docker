package com.cargowhale.docker.client.containers.info.stats

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.Canonical

@Canonical
class ContainerCpuStats {

    ContainerCpuUsage cpuUsage
    Long systemCpuUsage
    Map<String, Long> throttlingData

    @JsonGetter("throttlingData")
    Map<String, Long> getThrottlingData() {
        return this.throttlingData
    }

    @JsonSetter("throttling_data")
    void setSystemCpuUsage(Map<String, Long> throttlingData) {
        this.throttlingData = throttlingData
    }

    @JsonGetter("systemCpuUsage")
    Long getSystemCpuUsage() {
        return this.systemCpuUsage
    }

    @JsonSetter("system_cpu_usage")
    void setSystemCpuUsage(Long systemCpuUsage) {
        this.systemCpuUsage = systemCpuUsage
    }

    @JsonGetter("cpuUsage")
    ContainerCpuUsage getCpuUsage() {
        return cpuUsage
    }

    @JsonSetter("cpu_usage")
    void setCpuUsage(ContainerCpuUsage cpuUsage) {
        this.cpuUsage = cpuUsage
    }
}
