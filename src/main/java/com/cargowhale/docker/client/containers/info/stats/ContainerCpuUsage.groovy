package com.cargowhale.docker.client.containers.info.stats

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.Canonical

@Canonical
class ContainerCpuUsage {

    List<Long> cpuCoreUsage
    Long userModeUsage
    Long kernelModeUsage
    Long totalUsage

    @JsonGetter("cpuCoreUsage")
    List<Long> getCpuCoreUsage() {
        return this.cpuCoreUsage
    }

    @JsonSetter("percpu_usage")
    void setCpuCoreUsage(final List<Long> cpuCoreUsage) {
        this.cpuCoreUsage = cpuCoreUsage
    }

    @JsonGetter("userModeUsage")
    Long getUserModeUsage() {
        return this.userModeUsage
    }

    @JsonSetter("usage_in_usermode")
    void setUserModeUsage(final Long userModeUsage) {
        this.userModeUsage = userModeUsage
    }

    @JsonGetter("kernelModeUsage")
    Long getKernelModeUsage() {
        return this.kernelModeUsage
    }

    @JsonSetter("usage_in_kernelmode")
    void setKernelModeUsage(final Long kernelModeUsage) {
        this.kernelModeUsage = kernelModeUsage
    }

    @JsonGetter("totalUsage")
    Long getTotalUsage() {
        return this.totalUsage
    }

    @JsonSetter("total_usage")
    void setTotalUsage(final Long totalUsage) {
        this.totalUsage = totalUsage
    }
}
