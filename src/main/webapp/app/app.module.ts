import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";
import {CommonModule} from "@angular/common";

import {LayoutsModule} from "./shared/layouts/layouts.module";
import {PagesModule} from "./pages/pages.module";
import {PagesComponent} from "./pages/pages.component";

@NgModule({
    imports: [
        CommonModule,
        BrowserModule,
        HttpModule,
        LayoutsModule,
        PagesModule
    ],
    bootstrap: [PagesComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CargoWhaleAppModule {
}
