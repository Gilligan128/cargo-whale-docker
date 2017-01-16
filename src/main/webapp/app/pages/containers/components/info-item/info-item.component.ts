import {Component, Input} from "@angular/core";

@Component({
    selector: 'cw-info-item',
    template: `<div class="info-item">
                   <span class="name">{{name}}</span>
                   <ng-content></ng-content>
               </div>
               <cw-divider *ngIf="divider"></cw-divider>`
})
export class InfoItemComponent {
    @Input() name: string;
    @Input() divider = true;
}
