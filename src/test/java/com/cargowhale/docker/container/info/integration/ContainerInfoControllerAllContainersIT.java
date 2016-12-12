package com.cargowhale.docker.container.info.integration;

import com.cargowhale.docker.client.containers.ContainerState;
import com.cargowhale.docker.client.containers.info.list.ContainerListItem;
import com.cargowhale.docker.client.core.DockerRestTemplate;
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

import java.util.List;

import static com.cargowhale.docker.test.ControllerTestUtils.getForType;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContainerInfoControllerAllContainersIT {

    private static class ContainerIndexResourceType extends ParameterizedTypeReference<ContainerIndexResource> {
    }

    @MockBean
    private DockerRestTemplate restTemplate;

    @Autowired
    private TestRestTemplate client;

    @Test
    public void getAllContainers_NoContainers() {
        ContainerListItem[] containerArray = Arrays.array();

        when(this.restTemplate.getForObject("/v1.24/containers/json?all=1", ContainerListItem[].class)).thenReturn(containerArray);

        ResponseEntity<ContainerIndexResource> response = getForType(this.client, "/api/containers", new ContainerIndexResourceType());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ContainerIndexResource containerIndex = response.getBody();
        List<ContainerListItemResource> containerList = containerIndex.getContainers();
        assertThat(containerList, hasSize(0));
    }

    @Test
    public void getAllContainers_OneContainers() {
        ContainerListItem containerListItem1 = new ContainerListItem(ContainerState.CREATED, "78nm12hb3", "test-image", "47jk189nbk1", Arrays.array("test-container1"), "Created 3 days ago");
        ContainerListItem[] containerArray = Arrays.array(containerListItem1);

        when(this.restTemplate.getForObject("/v1.24/containers/json?all=1", ContainerListItem[].class)).thenReturn(containerArray);

        ResponseEntity<ContainerIndexResource> response = getForType(this.client, "/api/containers", new ContainerIndexResourceType());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ContainerIndexResource containerIndex = response.getBody();
        List<ContainerListItemResource> containerList = containerIndex.getContainers();
        assertThat(containerList, hasSize(1));

        assertThat(containerList.get(0), is(containerListItem1));
    }

    @Test
    public void getAllContainers_MultipleContainers() {
        ContainerListItem containerListItem1 = new ContainerListItem(ContainerState.CREATED, "78nm12hb3", "test-image", "47jk189nbk1", Arrays.array("test-container1"), "Created 3 days ago");
        ContainerListItem containerListItem2 = new ContainerListItem(ContainerState.RUNNING, "nu91o2n3b", "test-image", "nm2198nk321", Arrays.array("test-container2"), "Up 6 days");
        ContainerListItem[] containerArray = Arrays.array(containerListItem1, containerListItem2);

        when(this.restTemplate.getForObject("/v1.24/containers/json?all=1", ContainerListItem[].class)).thenReturn(containerArray);

        ResponseEntity<ContainerIndexResource> response = getForType(this.client, "/api/containers", new ContainerIndexResourceType());

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        ContainerIndexResource containerIndex = response.getBody();
        List<ContainerListItemResource> containerList = containerIndex.getContainers();
        assertThat(containerList, hasSize(2));

        assertThat(containerList.get(0), equalTo(containerListItem1));
        assertThat(containerList.get(1), equalTo(containerListItem2));
    }
}
