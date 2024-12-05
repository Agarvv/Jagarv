import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Store } from '@ngrx/store';
import { setLoading, setError, setSuccess, clearMessages } from '../../../store/admin/admin.actions';
import { finalize } from 'rxjs';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-reset-password-button',
  templateUrl: './reset-password-button.component.html',
  styleUrls: ['./reset-password-button.component.css']
})
export class ResetPasswordButtonComponent {
  @Input() form: FormGroup | null = null;
  private email: string | null = null;
  private token: string | null = null;

  constructor(
    private authService: AuthService,
    private store: Store,
    private route: ActivatedRoute
  ) {

    this.email = this.route.snapshot.paramMap.get('email');
    this.token = this.route.snapshot.paramMap.get('token');
  }

  sendResetPassword() { 
    this.store.dispatch(clearMessages());
     
    if (!this.form) {
      return;  // Form not provided, do nothing and return early.
    }
  
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    if (!this.email || !this.token) {
      console.error('Email or token is missing from the route parameters');
      return;
    }
    
    this.store.dispatch(setLoading({ isLoading: true }));
    
    const requestData = {
         email: this.email,
         token: this.token,
         password: this.form.value.password.trim()
     };
    
    
    console.log("final request data", requestData)

    this.authService.sendResetPassword(requestData).pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));
      })
    ).subscribe(
      (data) => {
        this.store.dispatch(setSuccess({ successMessage: 'Your Password Has Been Reset!' }));
      },
      (error) => {
        this.store.dispatch(setError({ errorMessage: error.error }));
        console.error('Error sending reset password', error); 
      }
    );
  }
}