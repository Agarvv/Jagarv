import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  resetPasswordForm: FormGroup | null = null;
  
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
      this.resetPasswordForm = this.fb.group({
          password: ['', [Validators.required]],
          resetPassword: ['', [Validators.required]]
      })
  }
}
