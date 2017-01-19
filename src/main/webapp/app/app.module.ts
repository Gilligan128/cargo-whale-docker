import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {HttpModule} from "@angular/http";

import {LayoutsModule} from "./shared/layouts/layouts.module";
import {MainComponent} from "./shared/layouts/main/main.component";

@NgModule({
    imports: [
        BrowserModule,
        HttpModule,
        LayoutsModule
    ],
    bootstrap: [MainComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class CargoWhaleAppModule {
}
