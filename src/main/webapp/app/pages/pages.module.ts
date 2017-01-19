import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {CommonModule} from "@angular/common";

import {PagesComponent} from "./pages.component";
import {LayoutsModule} from "../shared/layouts";

@NgModule({
    imports: [
        CommonModule,
        LayoutsModule
    ],
    declarations: [PagesComponent],
    exports: [PagesComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PagesModule {
}