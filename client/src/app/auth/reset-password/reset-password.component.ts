import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';

@Component({
  selector: 'app-reset-password',
  templateUrl: './reset-password.component.html',
  styleUrl: './reset-password.component.css'
})
export class ResetPasswordComponent {
  resetPasswordForm: FormGroup;
  
  constructor(
    private fb: FormBuilder,
    private authService: AuthService,
  ) {
      this.resetPasswordForm = this.fb.group({
          password: ['', [Validators.required]],
      })
  }
}