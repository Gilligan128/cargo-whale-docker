import {Component, OnInit} from "@angular/core";

import {SidebarItem} from "./sidebar-item/sidebar-item.model";

@Component({
    selector: 'cw-sidebar',
    templateUrl: 'app/shared/layouts/sidebar/sidebar.html'
})
export class SidebarComponent implements OnInit {

    private sidebarItems: SidebarItem[];

    ngOnInit(): void {
        this.sidebarItems = [
            {title: 'Dashboard', icon: 'dashboard', path: 'dashboard'},
            {title: 'Containers', icon: 'list', path: 'containers'}];
    }
}