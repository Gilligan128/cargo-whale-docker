import {Component, Input} from "@angular/core";

@Component({
    selector: 'cw-card',
    templateUrl: 'app/shared/layouts/card/card.html'
})
export class CardComponent {
    @Input() link: boolean;
}
