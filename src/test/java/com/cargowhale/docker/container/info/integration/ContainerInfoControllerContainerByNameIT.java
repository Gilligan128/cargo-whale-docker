package com.cargowhale.docker.container.info.integration;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.cargowhale.docker.container.info.model.ContainerDetails;
import com.cargowhale.docker.container.info.model.ContainerDetailsState;
import com.cargowhale.docker.container.info.resource.ContainerDetailsResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static com.cargowhale.docker.test.ControllerTestUtils.getForType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerInfoControllerContainerByNameIT {

    private static class ContainerDetailsResourceType extends ParameterizedTypeReference<ContainerDetailsResource> {
    }

    @MockBean
    private DockerRestTemplate restTemplate;

    @Autowired
    private TestRestTemplate client;

    @Test
    public void getContainerById() throws Exception {
        String containerId = "7vbk17823b";

        ContainerDetailsState containerDetailsState = new ContainerDetailsState(ContainerState.RUNNING, 123, "", 9, "2016-11-21T15:47:32Z");
        ContainerDetails containerDetails = new ContainerDetails(containerDetailsState, containerId, "cool-container", "cool-image-id", "/bin/sh");

        when(this.restTemplate.getForObject("/v1.24/containers/" + containerId + "/json", ContainerDetails.class)).thenReturn(containerDetails);

        ResponseEntity<ContainerDetailsResource> response = getForType(this.client, "/api/containers/" + containerId, new ContainerDetailsResourceType());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ContainerDetailsResource body = response.getBody();
        assertThat(body, is(containerDetails));
    }
}
