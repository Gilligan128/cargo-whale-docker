package com.cargowhale.docker.client.core.exception;

import com.cargowhale.docker.client.containers.ContainerState;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static java.lang.String.format;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Container in bad state")
public class BadContainerStateException extends RuntimeException {

    public BadContainerStateException(final ContainerState state) {
        super(format("Container in %s state", state.getState()));
    }
}
