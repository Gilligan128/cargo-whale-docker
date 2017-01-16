import {Component, Input} from "@angular/core";
import {SidebarItem} from "./sidebar-item.model";

@Component({
    selector: 'cw-sidebar-item',
    templateUrl: 'app/shared/layouts/sidebar/sidebar-item/sidebar-item.html'
})
export class SidebarItemComponent {
    @Input() sidebarItem: SidebarItem;
}
