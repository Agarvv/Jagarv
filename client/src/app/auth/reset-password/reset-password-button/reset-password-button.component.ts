import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms'
import { AuthService } from '../../../services/auth/auth.service';
import { Store } from '@ngrx/store';
import { setLoading, setError, setSuccess, clearMessages } from '../../../store/admin/admin.actions'
import { finalize } from 'rxjs';

@Component({
  selector: 'app-reset-password-button',
  templateUrl: './reset-password-button.component.html',
  styleUrl: './reset-password-button.component.css'
})
export class ResetPasswordButtonComponent {
  @Input() form: FormGroup;


 constructor(private authService: AuthService, private store: Store) {}

 sendResetPassword() { 
     this.store.dispatch(clearMessages());
     
  if(!this.form) {
    return;  // Form not provided, do nothing and return early.
  }
  
   if (this.form && this.form.invalid) {
     this.form.markAllAsTouched();
     return;
   }

   this.authService.sendResetPassword(this.form.value).pipe(
    finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }))
    })
   ).subscribe((data) => {
       this.store.dispatch(setSuccess({ successMessage: 'Your Password Has Been Reset!' }));
   }, (error) => {
       this.store.dispatch(setError({ errorMessage: error.error }));
        console.error('Error sending reset password', error); 
   })
   
   
   
 }


}