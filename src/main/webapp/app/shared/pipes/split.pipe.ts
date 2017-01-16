import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'split'
})
export class SplitPipe implements PipeTransform {

    transform(input: string, char: string, index = 0): string {
        if (input === null) {
            return '';
        }
        if (char === null) {
            return input;
        }
        return input.split(char)[index];
    }
}