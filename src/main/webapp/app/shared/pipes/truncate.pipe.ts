import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'truncate'
})
export class TruncateCharactersPipe implements PipeTransform {

    transform(input: string, chars: number, breakOnWord?: boolean): string {
        if (isNaN(chars)) {
            return input;
        }
        if (chars <= 0) {
            return '';
        }
        if (input && input.length > chars) {
            input = input.substring(0, chars);

            if (!breakOnWord) {
                let lastSpace = input.lastIndexOf(' ');
                // Get last space
                if (lastSpace !== -1) {
                    input = input.substr(0, lastSpace);
                }
            } else {
                while (input.charAt(input.length - 1) === ' ') {
                    input = input.substr(0, input.length - 1);
                }
            }
            return input + '...';
        }
        return input;
    }
}