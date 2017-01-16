import {Resolve, ActivatedRouteSnapshot, RouterStateSnapshot} from "@angular/router";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {ContainerDetails} from "./container-details.model";
import {ContainersService} from "../../containers.service";

@Injectable()
export class ContainerDetailsResolver implements Resolve<ContainerDetails> {

    constructor(private service: ContainersService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ContainerDetails> {
        let id: string = route.params['id'];

        return this.service.getContainerDetails<ContainerDetails>(id);
    }
}