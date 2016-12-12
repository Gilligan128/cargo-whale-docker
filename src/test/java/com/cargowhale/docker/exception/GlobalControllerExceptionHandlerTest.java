package com.cargowhale.docker.exception;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.client.HttpServerErrorException;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GlobalControllerExceptionHandlerTest {

    @InjectMocks
    private GlobalControllerExceptionHandler exceptionHandler;

    @Test
    public void handleBadFilterCorrectlyFormatsErrorMessage() throws Exception {
        HttpServletRequest request = mock(HttpServletRequest.class);
        BindException bindException = mock(BindException.class);

        String path = "/some/okay/uri";
        String message = "Bad Filter";

        when(request.getRequestURI()).thenReturn(path);

        ResponseEntity<CargoWhaleErrorMessage> responseEntity = this.exceptionHandler.handleBadFilter(request, bindException);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.BAD_REQUEST));

        CargoWhaleErrorMessage errorMessage = responseEntity.getBody();

        assertThat(errorMessage.getPath(), is(path));
        assertThat(errorMessage.getMessage(), is(message));
        assertThat(errorMessage.getError(), is(bindException.getClass().toString()));
    }

    @Test
    public void handleContainerNotRunningWhenRetrievingProcesses() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServerErrorException exception = mock(HttpServerErrorException.class);
        String responseError = "{\"message\":\"is not running\"}";

        String path = "/some/okay/top";
        String message = "is not running";
        String error = HttpStatus.BAD_REQUEST.toString();

        when(request.getRequestURI()).thenReturn(path);
        when(exception.getResponseBodyAsString()).thenReturn(responseError);
        when(exception.getMessage()).thenReturn(error);

        ResponseEntity<CargoWhaleErrorMessage> responseEntity = this.exceptionHandler.handleServerSideErrorFromDocker(request, exception);
        CargoWhaleErrorMessage errorMessage = responseEntity.getBody();

        assertThat(errorMessage.getPath(), is(path));
        assertThat(errorMessage.getMessage(), is(message));
        assertThat(errorMessage.getError(), is(error));

    }

    @Test
    public void handleOtherRandomDockerError() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServerErrorException exception = mock(HttpServerErrorException.class);
        String responseError = "{\"message\":\"Some not okay error\"}";

        String path = "/some/okay/uri";
        String message = "Some not okay error";
        String error = HttpStatus.BAD_GATEWAY.toString();

        when(request.getRequestURI()).thenReturn(path);
        when(exception.getResponseBodyAsString()).thenReturn(responseError);
        when(exception.getMessage()).thenReturn(error);

        ResponseEntity<CargoWhaleErrorMessage> responseEntity = this.exceptionHandler.handleServerSideErrorFromDocker(request, exception);
        CargoWhaleErrorMessage errorMessage = responseEntity.getBody();

        assertThat(errorMessage.getPath(), is(path));
        assertThat(errorMessage.getMessage(), is(message));
        assertThat(errorMessage.getError(), is(error));

    }
}