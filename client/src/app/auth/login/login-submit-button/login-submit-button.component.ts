import { Component, Input } from '@angular/core';
import { FormGroup} from '@angular/forms';
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-login-submit-button',
  templateUrl: './login-submit-button.component.html',
  styleUrl: './login-submit-button.component.css'
})
export class LoginSubmitButtonComponent {
  @Input() form!: FormGroup;
  
  constructor(private authService: AuthService) { }

  submitForm() {
    if(this.form.invalid) {
      this.form.markAllAsTouched();
      return 
    }

    this.authService.loginUser(this.form.value).subscribe((data)=> {
      console.log("login success", data)
    }, (error) => {
       console.log("login failure", error)
    })
  }

}
