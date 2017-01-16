import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from "@angular/core";
import {CommonModule} from "@angular/common";
import {JoinPipe} from "./join.pipe";
import {KeysPipe} from "./keys.pipe";
import {ReplaceCharPipe} from "./replace.pipe";
import {SplitPipe} from "./split.pipe";
import {TruncateCharactersPipe} from "./truncate.pipe";

const PIPES = [
    JoinPipe,
    KeysPipe,
    ReplaceCharPipe,
    SplitPipe,
    TruncateCharactersPipe
];

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        ...PIPES
    ],
    exports: [
        ...PIPES
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class PipesModule {
}