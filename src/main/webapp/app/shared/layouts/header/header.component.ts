import {Component, OnInit} from "@angular/core";
import {Router, NavigationEnd} from "@angular/router";

@Component({
    selector: 'cw-header',
    template: `
<div class="header clearfix">
    <ol class="breadcrumb">
        <li class="breadcrumb-item" *ngFor="let segment of segments; let last = last" [ngClass]="{active: last}">
            <a *ngIf="!last" [routerLink]="segment">{{prettyName(segment) | truncate : 12 }}</a>
            <span *ngIf="last">{{prettyName(segment) | truncate : 12 }}</span>
        </li>
    </ol>
</div>`
})
export class HeaderComponent implements OnInit {

    private segments: string[];

    constructor(private router: Router) {
    }

    ngOnInit(): void {
        this.router.events.subscribe((end: NavigationEnd) => {
            this.segments = [];
            this.parseSegments(end.urlAfterRedirects ? end.urlAfterRedirects : end.url);
        });
    }

    private parseSegments(url: string) {
        this.segments.unshift(url);

        if (url.lastIndexOf('/') > 0) {
            this.parseSegments(url.substr(0, url.lastIndexOf('/')));
        }
    }

    private prettyName(url: string): string {
        return url.substr(url.lastIndexOf('/') + 1);
    }
}