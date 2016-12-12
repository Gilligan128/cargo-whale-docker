package com.cargowhale.docker.container.info.integration;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.containers.info.list.ContainerListItem;
import com.cargowhale.docker.client.core.DockerRestTemplate;
import com.cargowhale.docker.container.info.index.ContainerIndex;
import com.cargowhale.docker.container.info.index.ContainerIndexResource;
import com.cargowhale.docker.container.info.index.ContainerListItemResource;
import org.assertj.core.util.Arrays;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URISyntaxException;
import java.util.List;

import static com.cargowhale.docker.test.ControllerTestUtils.getForType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerInfoControllerFilteredContainersIT {

    private static class ContainerListResponseItemIndexResourceType extends ParameterizedTypeReference<ContainerIndexResource> {
    }

    @MockBean
    private DockerRestTemplate restTemplate;

    @Autowired
    private TestRestTemplate client;

    @Test
    public void getFilteredContainers_Created() throws Exception {
        verifySingleStateFilter(ContainerState.CREATED);
    }

    @Test
    public void getFilteredContainers_Restarting() throws Exception {
        verifySingleStateFilter(ContainerState.RESTARTING);
    }

    @Test
    public void getFilteredContainers_Running() throws Exception {
        verifySingleStateFilter(ContainerState.RUNNING);
    }

    @Test
    public void getFilteredContainers_Paused() throws Exception {
        verifySingleStateFilter(ContainerState.PAUSED);
    }

    @Test
    public void getFilteredContainers_Exited() throws Exception {
        verifySingleStateFilter(ContainerState.EXITED);
    }

    @Test
    public void getFilteredContainers_Dead() throws Exception {
        verifySingleStateFilter(ContainerState.DEAD);
    }

    private void verifySingleStateFilter(final ContainerState containerState) throws URISyntaxException {
        ContainerListItem containerListItem = new ContainerListItem(ContainerState.CREATED, "78nm12hb3", "test-image", "47jk189nbk1", Arrays.array("test-container1"), "Created 3 days ago");
        ContainerListItem[] containerList = Arrays.array(containerListItem);

        String path = "/v1.24/containers/json";
        UriComponentsBuilder builder = UriComponentsBuilder.fromPath(path).queryParam("filters", "{\"status\":[\"" + containerState.state + "\"]}");

        when(this.restTemplate.getForObject(builder.toUriString(), ContainerListItem[].class)).thenReturn(containerList);

        ResponseEntity<ContainerIndexResource> response = getForType(this.client, "/api/containers?state=" + containerState.state, new ContainerListResponseItemIndexResourceType());

        verify(this.restTemplate).getForObject(builder.toUriString(), ContainerListItem[].class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ContainerIndexResource containerIndex = response.getBody();
        List<ContainerListItemResource> containerListResponse = containerIndex.getContainers();
        assertThat(containerListResponse, hasSize(1));

        assertThat(containerListResponse, contains(containerListItem));
    }

    @Test
    public void verifyBadFilterReturnsHttpBadRequest() {
        String state = "I_AM_A_TEAPOT";

        ResponseEntity<ContainerIndex> response = this.client.getForEntity("/api/containers?state=" + state, ContainerIndex.class);

        assertThat(response.getStatusCode(), is(HttpStatus.BAD_REQUEST));
    }
}
