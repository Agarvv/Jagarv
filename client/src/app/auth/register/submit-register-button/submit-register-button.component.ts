import { Component, Input } from '@angular/core';
import { AuthService } from '@services/auth/auth.service';
import { FormGroup} from '@angular/forms';


@Component({
  selector: 'app-submit-register-button',
  templateUrl: './submit-register-button.component.html',
  styleUrl: './submit-register-button.component.css'
})
export class SubmitRegisterButtonComponent {
   @Input form!: FormGroup;
    
   constructor(private authService: AuthService) {
       
   }
   
   submitForm() {
       if(this.form.invalid) {
           this.form.markAllAsTouched();
           return 
       }
       
       authService.registerUser(this.form.value).subscribe((data) => {
           console.log("All Ok", data)
       }, (error) => {
           console.error("ERR REGISTER", error)
       })
   }
}
