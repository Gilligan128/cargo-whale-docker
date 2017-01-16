package com.cargowhale.docker.client.containers;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ContainerState {

    ALL("all"),
    CREATED("created"),
    RESTARTING("restarting"),
    RUNNING("running"),
    PAUSED("paused"),
    EXITED("exited"),
    DEAD("dead");

    public static ContainerState from(final String state) {
        return valueOf(state.toUpperCase());
    }

    private final String state;

    ContainerState(final String state) {
        this.state = state;
    }

    @JsonValue
    public String getState() {
        return this.state;
    }
}
