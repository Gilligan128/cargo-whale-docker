package com.pillartechnology.pluggedinmetrics.index;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class DemoController {

    private static final Random RANDOM = new Random();

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
        randomWait();
        return ResponseEntity.status(HttpStatus.OK).body(new DemoResource("We happy! :)"));
    }

    @RequestMapping(value = "/sad",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "endpoint.sad", absolute = true)
    public ResponseEntity<DemoResource> sad() {
        randomWait();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DemoResource("We sad... :("));
    }

    @RequestMapping(value = "/mad",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed(name = "endpoint.mad", absolute = true)
    public ResponseEntity<DemoResource> mad() {
        randomWait();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new DemoResource("We mad >:("));
    }

    private static void randomWait() {
        double waitTime = 500 * RANDOM.nextGaussian();
        long startTime = System.currentTimeMillis();

        while (waitTime + startTime > System.currentTimeMillis()) {
            Thread.yield();
        }
    }
}
