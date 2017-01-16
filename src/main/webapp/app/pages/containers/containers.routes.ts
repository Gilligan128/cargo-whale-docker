import {Routes, RouterModule} from "@angular/router";
import {ContainersComponent} from "./containers.component";
import {ContainerIndexComponent} from "./components/index/container-index.component";
import {ContainerDetailsComponent} from "./components/details/container-details.component";
import {ContainerEnvironmentComponent} from "./components/details/components/environment/environment.component";
import {ContainerNetworkComponent} from "./components/details/components/networks/networks.component";
import {ContainerDetailsResolver} from "./components/details/container-details.resolver";

const routes: Routes = [{
    path: 'containers',
    component: ContainersComponent,
    children: [
        {
            path: '',
            component: ContainerIndexComponent
        },
        {
            path: ':id',
            component: ContainerDetailsComponent,
            resolve: {
                container: ContainerDetailsResolver
            },
            children: [
                {path: '', redirectTo: 'environment', pathMatch: 'full'},
                {path: 'environment', component: ContainerEnvironmentComponent},
                {path: 'network', component: ContainerNetworkComponent}
            ]
        }
    ]
}
];

export const containersRoutes = RouterModule.forChild(routes);
