import {Routes, RouterModule} from "@angular/router";

const routes: Routes = [
    {path: '', redirectTo: 'containers', pathMatch: 'full'}
];

export const appRoutes = RouterModule.forRoot(routes, {useHash: true});
