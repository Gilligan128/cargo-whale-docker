import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'keys'
})
export class KeysPipe implements PipeTransform {

    transform(input: any): any[] {
        let keys = [];

        for (let key in input) {
            keys.push(key);
        }

        return keys;
    }
}