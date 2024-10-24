import { AbstractControl } from '@angular/forms';

// This validator checks if a array has at least one element
// if the .length() of the array is equal to 0, the validator is'nt passed,
// if the .length() of the array is bigger than 0, the validator is passed.
export class NonEmptyArrayValidator {
  static nonEmptyArray(control: AbstractControl) {
    const arr = control.value;
    if(!Array.isArray(arr) || arr.length === 0) { // strict operator
        return { emptyArray: true }; // invalid array
    }
    return null // valid array
  }
}