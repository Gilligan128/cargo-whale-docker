import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {ContainerNetworkSettings} from "../../container-details.model";

@Component({
    selector: 'cw-container-network',
    template: `<div>
                   <pre>
                       {{networkSettings | json}}
                   </pre>
               </div>`
})
export class ContainerNetworkComponent implements OnInit {

    private networkSettings: ContainerNetworkSettings;

    constructor(private route: ActivatedRoute) {
    }

    ngOnInit(): void {
        this.networkSettings = this.route.snapshot.parent.data['container'].networkSettings;
    }
}