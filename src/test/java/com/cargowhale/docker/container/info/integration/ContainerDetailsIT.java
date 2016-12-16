package com.cargowhale.docker.container.info.integration;

import com.cargowhale.division.MockServiceBuilder;
import com.cargowhale.docker.test.integration.BaseIntegrationTest;
import com.cargowhale.docker.test.integration.RamlSpecFiles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.cargowhale.division.matchers.RequestSpecMatcher.responseIsInSpec;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class ContainerDetailsIT extends BaseIntegrationTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private MockServiceBuilder dockerServiceBuilder;

    @Test
    public void getContainerDetails() throws Exception {
        this.dockerServiceBuilder.expectRequest("/v1.24/containers/running-container/json", HttpMethod.GET, HttpStatus.OK, MediaType.APPLICATION_JSON);

        this.client.perform(get("/api/containers/running-container"))
            .andExpect(responseIsInSpec(RamlSpecFiles.CARGO_WHALE_RAML_SPEC_FILE)
                .with("/api/containers/{id}", HttpMethod.GET, HttpStatus.OK, MediaTypes.HAL_JSON));
    }
}