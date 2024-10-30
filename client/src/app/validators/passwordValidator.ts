
import { AbstractControl, ValidationErrors } from '@angular/forms';

export class PasswordValidator {
  static isValidPassword(control: AbstractControl): ValidationErrors | null {
    const value = control.value;
    const valid = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(value);
    return valid ? null : { weakPassword: true };
  }
}