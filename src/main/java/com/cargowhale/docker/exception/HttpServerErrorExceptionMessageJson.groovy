package com.cargowhale.docker.exception

import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.Canonical

@Canonical
class HttpServerErrorExceptionMessageJson {
    String message;

    HttpServerErrorExceptionMessageJson(@JsonProperty("message") String message){
        this.message = message
    }
}
