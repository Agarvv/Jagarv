import { AbstractControl } from '@angular/forms';

export class PriceValidator {
  static isValidPrice(control: AbstractControl) {
    const value = control.value;
    if(value <= 0 || isNaN(value)) {
        return { invalidPrice: true }; // invalid price
    }

    return null; // valid price
  }
}