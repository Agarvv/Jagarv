import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service'; 
import { Store } from '@ngrx/store';
import { setLoading, setError, setSuccess, clearMessages } from '../../store/admin/admin.actions'


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
