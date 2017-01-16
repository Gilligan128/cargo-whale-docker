import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {CommonModule} from "@angular/common";
import {RouterModule} from "@angular/router";
import {CardComponent} from "./card/card.component";
import {HeaderComponent} from "./header/header.component";
import {MainComponent} from "./main/main.component";
import {SidebarComponent} from "./sidebar/sidebar.component";
import {SidebarItemComponent} from "./sidebar/sidebar-item/sidebar-item.component";
import {DividerComponent} from "./divider/divider.component";
import {PipesModule} from "../pipes";

const DECLARED_COMPONENTS = [
    CardComponent,
    HeaderComponent,
    MainComponent,
    DividerComponent,
    SidebarComponent,
    SidebarItemComponent
];

const EXPORTED_COMPONENTS = [
    CardComponent,
    MainComponent,
    DividerComponent
];

@NgModule({
    imports: [
        CommonModule,
        RouterModule,
        PipesModule
    ],
    declarations: [
        ...DECLARED_COMPONENTS
    ],
    exports: [
        ...EXPORTED_COMPONENTS
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class LayoutsModule {
}