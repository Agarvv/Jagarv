import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms'
import { AuthService } from '../../../services/auth/auth.service';

@Component({
  selector: 'app-reset-password-button',
  templateUrl: './reset-password-button.component.html',
  styleUrl: './reset-password-button.component.css'
})
export class ResetPasswordButtonComponent {
 @Input() form?: FormGroup

 constructor(private authService: AuthService) {}

 sendResetPassword() { 
  if(!this.form) {
    return;  // Form not provided, do nothing and return early.
  }
  
   if (this.form && this.form.invalid) {
     this.form.markAllAsTouched();
     return;
   }

   this.authService.sendResetPassword(this.form.value).subscribe((data) => {
     console.log("Reset Password sent successfully", data
      
     )
   }, (error) => {
    console.log("error while sending rset password", error)
   });
 }


}
