package com.cargowhale.docker.images;

import com.cargowhale.docker.config.docker.DockerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ImageInfoController {

    private final DockerProperties properties;
    private final RestTemplate restTemplate;

    @Autowired
    public ImageInfoController(final RestTemplate restTemplate, final DockerProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    @RequestMapping(value = "/images",
        method = RequestMethod.GET,
        produces = MediaTypes.HAL_JSON_VALUE)
    public String getImages() {
        String dockerUri = this.properties.getUri();

        return this.restTemplate.getForObject(dockerUri + "/images/json", String.class);
    }
}
