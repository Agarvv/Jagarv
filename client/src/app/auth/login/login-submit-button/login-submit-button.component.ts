import { Component, Input } from '@angular/core';
import { FormGroup} from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';
import { Store } from '@ngrx/store';
import { setLoading, setError, clearMessages } from './../../../store/admin/admin.actions'
import { finalize } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-submit-button',
  templateUrl: './login-submit-button.component.html',
  styleUrl: './login-submit-button.component.css'
})
export class LoginSubmitButtonComponent {
  @Input() form!: FormGroup;
  
  constructor(private authService: AuthService, private store: Store, private router: Router) { }

  submitForm() {
    this.store.dispatch(clearMessages());
    if(this.form.invalid) {
      this.form.markAllAsTouched();
      return 
    }

    this.store.dispatch(setLoading({ isLoading: true }));
    
    this.authService.loginUser(this.form.value).pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));
      })
    ).subscribe((data) => {
      this.router.navigate(['/'])
    }, (error) => {
      console.log(error) // debug for "developers"
      this.store.dispatch(setError({ errorMessage: error.error }))
    })

  }

}
