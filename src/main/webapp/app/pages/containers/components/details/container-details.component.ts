import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ContainerDetails} from "./container-details.model";

@Component({
    selector: 'cw-container-details',
    templateUrl: 'app/pages/containers/components/details/container-details.html'
})
export class ContainerDetailsComponent implements OnInit {

    private details: ContainerDetails;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.details = this.route.snapshot.data['container'];
    }
}