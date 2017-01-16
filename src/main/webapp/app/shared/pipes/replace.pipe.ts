import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'replace'
})
export class ReplaceCharPipe implements PipeTransform {

    transform(input: string, oldChar: string, newChar = ''): string {
        if (input === null) {
            return '';
        }
        if (oldChar === null) {
            return input;
        }
        return input.replace(oldChar, newChar);
    }
}