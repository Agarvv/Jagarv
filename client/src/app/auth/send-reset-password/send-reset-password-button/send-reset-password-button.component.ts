import { Component, Input } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { FormGroup } from '@angular/forms'
import { Store } from '@ngrx/store';
import { setLoading, setError, setSuccess, clearMessages } from '../../../store/admin/admin.actions'
import { finalize } from 'rxjs';

@Component({
  selector: 'app-send-reset-password-button',
  templateUrl: './send-reset-password-button.component.html',
  styleUrl: './send-reset-password-button.component.css'
})
export class SendResetPasswordButtonComponent {
 @Input() form: FormGroup | null = null;

 
 constructor(private authService: AuthService, private store: Store) {
     
 }
 
 sendResetPasswordEmail() {
    this.store.dispatch(clearMessages());

    if (!this.form || this.form.invalid) { 
        this.form?.markAllAsTouched(); 
        return;
    }

    this.store.dispatch(setLoading({ isLoading: true }));
    
    this.authService.sendResetPassword(this.form.value).pipe(
        finalize(()=> {
            this.store.dispatch(setLoading({ isLoading: false }));
        })
    ).subscribe((data) => {
        this.store.dispatch(setSuccess({ successMessage: 'Check Your Email, We have justs sent You the instructions.' }));
    }, (error) => {
        this.store.dispatch(setError({ errorMessage: error.error }));
        console.error('Error sending reset password email:', error); // debug
    })


}

 
}
