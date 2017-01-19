package com.cargowhale.docker.index;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    public class DemoResource {

        public final String message;

        DemoResource(final String message) {
            this.message = message;
        }
    }

    @RequestMapping(value = "/happy",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "endpoint.happy", absolute = true)
    public ResponseEntity<DemoResource> happy() {
        return ResponseEntity.status(HttpStatus.OK).body(new DemoResource("We happy! :)"));
    }

    @RequestMapping(value = "/sad",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "endpoint.sad", absolute = true)
    public ResponseEntity<DemoResource> sad() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DemoResource("We sad... :("));
    }

    @RequestMapping(value = "/mad",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "endpoint.mad", absolute = true)
    public ResponseEntity<DemoResource> mad() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemoResource("We mad >:("));
    }
}
