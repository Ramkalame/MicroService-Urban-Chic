import { AbstractControl, ValidatorFn } from "@angular/forms";

export function numericValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any } | null => {
      const isNumeric = /^\d+$/.test(control.value);
      return isNumeric ? null : { 'numeric': { value: control.value } };
    };
  }