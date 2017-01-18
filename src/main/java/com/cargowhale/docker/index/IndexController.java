package com.cargowhale.docker.index;

import com.codahale.metrics.annotation.Timed;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    @RequestMapping(value = "/happy",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    @Timed(name = "endpoint.happy", absolute = true)
    public IndexResource happy() {
        return new IndexResource();
    }

    @RequestMapping(value = "/sad",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    @Timed(name = "endpoint.sad", absolute = true)
    public IndexResource sad() {
        return new IndexResource();
    }

    @RequestMapping(value = "/bad",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    @Timed(name = "endpoint.bad", absolute = true)
    public IndexResource bad() {
        return new IndexResource();
    }
}
