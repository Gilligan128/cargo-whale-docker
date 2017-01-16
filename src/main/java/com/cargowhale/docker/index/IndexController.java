package com.cargowhale.docker.index;

import com.codahale.metrics.annotation.Timed;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    @Timed(name = "endpoint.index", absolute = true)
    public IndexResource index() {
        return new IndexResource();
    }
}
