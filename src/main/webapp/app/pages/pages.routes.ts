import {Routes, RouterModule} from "@angular/router";

import {PagesComponent} from "./pages.component";

const routes: Routes = [
    {
        path: '',
        component: PagesComponent,
        children: [
            {path: 'containers', loadChildren: 'app/pages/containers/containers.module#ContainersModule'}
        ]
    }
];

export const pagesRoutes = RouterModule.forChild(routes);