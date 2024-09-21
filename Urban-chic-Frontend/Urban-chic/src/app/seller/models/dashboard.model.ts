import { Type } from "@angular/core";

export interface Widget{
    id:number;
    icon:string;
    label:string;
    content: Type<unknown>;
}