package com.cargowhale.docker.client.containers.info.stats

import com.fasterxml.jackson.annotation.JsonGetter
import com.fasterxml.jackson.annotation.JsonSetter
import groovy.transform.Canonical

@Canonical
class ContainerMemoryStats {

    Long maxUsage
    Long usage
    Long failCount
    Long limit

    @JsonGetter("maxUsage")
    Long getMaxUsage() {
        return maxUsage
    }

    @JsonSetter("max_usage")
    void setMaxUsage(final Long maxUsage) {
        this.maxUsage = maxUsage
    }

    @JsonGetter("usage")
    Long getUsage() {
        return usage
    }

    @JsonSetter("usage")
    void setUsage(final Long usage) {
        this.usage = usage
    }

    @JsonGetter("failCount")
    Long getFailCount() {
        return failCount
    }

    @JsonSetter("failcnt")
    void setFailCount(final Long failCount) {
        this.failCount = failCount
    }

    @JsonGetter("limit")
    Long getLimit() {
        return limit
    }

    @JsonSetter("limit")
    void setLimit(final Long limit) {
        this.limit = limit
    }
}
