package com.cargowhale.docker.client.containers

import groovy.transform.Canonical

@Canonical
class ListContainersParam {

    static ListContainersParam create(final String name, final String value) {
        return new ListContainersParam(name, value)
    }

    static ListContainersParam filter(final String name, final String value) {
        return new Filter(name, value)
    }

    static ListContainersParam state(final ContainerState state) {
        return filter("status", state.state)
    }

    static ListContainersParam allContainers(final boolean all) {
        return create("all", all ? "1" : "0")
    }

    static ListContainersParam allContainers() {
        return allContainers(true)
    }

    String name
    String value

    @Canonical
    static class Filter extends ListContainersParam {

        Filter(final String name, final String value) {
            super(name, value)
        }
    }
}
