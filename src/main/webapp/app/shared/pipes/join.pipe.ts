import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
    name: 'join'
})
export class JoinPipe implements PipeTransform {

    transform(input: string[]): string {
        if (input === null) {
            return '';
        }
        return input.join(' ');
    }
}