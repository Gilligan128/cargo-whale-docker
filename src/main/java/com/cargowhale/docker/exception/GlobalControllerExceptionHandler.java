package com.cargowhale.docker.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import groovy.transform.Canonical;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    public static final String IS_NOT_RUNNING = "is not running";
    public static final String TOP = "/top";

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Filter")
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<CargoWhaleErrorMessage> handleBadFilter(final HttpServletRequest request, final Exception ex) {
        return new ResponseEntity<>(new CargoWhaleErrorMessage(request.getRequestURI(), "Bad Filter", ex.getClass().toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    public ResponseEntity<CargoWhaleErrorMessage> handleServerSideErrorFromDocker(final HttpServletRequest request, final HttpServerErrorException ex) throws IOException {

        return generateErrorMessageBasedOnErrorAndRequest(request, ex);
    }

    private ResponseEntity<CargoWhaleErrorMessage> generateErrorMessageBasedOnErrorAndRequest(final HttpServletRequest request, final HttpServerErrorException ex) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HttpServerErrorExceptionMessageJson json = mapper.readValue(ex.getResponseBodyAsString(), HttpServerErrorExceptionMessageJson.class);

        String path = request.getRequestURI();
        String errorMessage = json.getMessage();
        String exception = ex.getMessage();

        if(errorMessage.contains(IS_NOT_RUNNING) && path.contains(TOP)){
            return new ResponseEntity<CargoWhaleErrorMessage>(new CargoWhaleErrorMessage(path, errorMessage, HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<CargoWhaleErrorMessage>(new CargoWhaleErrorMessage(path, errorMessage, exception), HttpStatus.BAD_GATEWAY);
        }
    }
}
