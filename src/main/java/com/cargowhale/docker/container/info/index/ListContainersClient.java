package com.cargowhale.docker.container.info.index;

import com.cargowhale.docker.client.containers.ListContainersParam;
import com.cargowhale.docker.client.core.DockerEndpointBuilder;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.spotify.docker.client.messages.Container;
import groovy.json.JsonOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Repository
public class ListContainersClient {

    private static final ParameterizedTypeReference<List<Container>> LIST_CONTAINER_TYPE = new ParameterizedTypeReference<List<Container>>() {
    };

    private final DockerRestTemplate restTemplate;
    private final DockerEndpointBuilder endpointBuilder;

    @Autowired
    public ListContainersClient(final DockerRestTemplate restTemplate, final DockerEndpointBuilder endpointBuilder) {
        this.restTemplate = restTemplate;
        this.endpointBuilder = endpointBuilder;
    }

    List<Container> listContainers(final ListContainersParam... params) {
        String listContainersEndpoint = this.endpointBuilder.getListContainersEndpoint();

        MultiValueMap<String, String> filters = new LinkedMultiValueMap<>();
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(listContainersEndpoint);
        for (final ListContainersParam param : params) {
            if (param instanceof ListContainersParam.Filter) {
                filters.add(param.getName(), param.getValue());
            } else {
                builder.queryParam(param.getName(), param.getValue());
            }
        }

        if (!filters.isEmpty()) {
            builder.queryParam("filters", "{filters}");
            return this.restTemplate.getForObject(builder.build().toString(), LIST_CONTAINER_TYPE, JsonOutput.toJson(filters));
        }

        return this.restTemplate.getForObject(builder.build().toString(), LIST_CONTAINER_TYPE);
    }
}
