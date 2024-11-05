import { Component, Input } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service'; 
import { FormGroup } from '@angular/forms';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from "../../../store/admin/admin.actions"
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-submit-register-button',
  templateUrl: './submit-register-button.component.html',
  styleUrls: ['./submit-register-button.component.css' ]  // Corrección aquí
})
export class SubmitRegisterButtonComponent {
   @Input() form!: FormGroup;
    
   constructor(private authService: AuthService, private store: Store) {}
   
   submitForm() {
     this.store.dispatch(clearMessages());
       if(this.form.invalid) {
           this.form.markAllAsTouched();
           return; 
       }

       this.store.dispatch(setLoading({ isLoading: true}));
       
       this.authService.registerUser(this.form.value).pipe( 
         finalize(() => {
           this.store.dispatch(setLoading({ isLoading: false })); 
         })
       ).subscribe(
         (data) => {
           this.store.dispatch(setSuccess({ successMessage: "Welcome To Jagarv! Please Wait While We Redirect You..."}));
         },
         (error) => {
           this.store.dispatch(setError({ errorMessage: error.error}));
         }
       );
   }
}
