import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {CommonModule} from "@angular/common";

import {PagesComponent} from "./pages.component";
import {pagesRoutes} from "./pages.routes";
import {ContainersModule} from "./containers";
import {LayoutsModule} from "../shared/layouts";

@NgModule({
    imports: [
        CommonModule,
        LayoutsModule,
        ContainersModule,
        pagesRoutes
    ],
    declarations: [PagesComponent],
    exports: [PagesComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PagesModule {
}