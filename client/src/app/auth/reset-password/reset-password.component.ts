import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  resetPasswordForm: FormGroup | null = null;
  
  constructor(private fb: FormBuilder) {
      this.resetPasswordForm = this.fb.group({
          password: ['', [validators.required]],
          resetPassword: ['', [validators.required]]
      })
  }
}
