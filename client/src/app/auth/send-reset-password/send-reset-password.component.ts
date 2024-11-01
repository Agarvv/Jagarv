import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-send-reset-password',
  templateUrl: './send-reset-password.component.html',
  styleUrl: './send-reset-password.component.css'
})
export class SendResetPasswordComponent {
  sendResetPasswordForm: FormGroup | null = null;
  
  constructor(
    private fb: FormBuilder,
    private authService: AuthService
  ) {
      this.sendResetPasswordForm = this.fb.group({
          email: ['', 
          [Validators.required, 
          Validators.email]
          ]
      })
  }
  
}
