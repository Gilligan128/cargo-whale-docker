import {Component, OnInit} from "@angular/core";

import {ContainersService} from "../../containers.service";
import {ContainerIndexItem} from "./container-index-item.model";
import {ContainerIndex} from "./container-index.model";
import {StateMetadata} from "./state-metadata.model";
import {Router} from "@angular/router";

@Component({
    selector: 'cw-container-list',
    templateUrl: 'app/pages/containers/components/index/container-index.html'
})
export class ContainerIndexComponent implements OnInit {

    private containers: ContainerIndexItem[];
    private stateMetadata: StateMetadata;

    constructor(private router: Router, private containerService: ContainersService) {
    }

    ngOnInit(): void {
        this.containerService.getContainerIndex<ContainerIndex>().subscribe(containerIndex => {
            this.containers = containerIndex.containers;
            this.stateMetadata = containerIndex.stateMetadata;
        });
    }

    onClickContainer(container: ContainerIndexItem): void {
        this.router.navigate(['/containers', container.id]);
    }
}