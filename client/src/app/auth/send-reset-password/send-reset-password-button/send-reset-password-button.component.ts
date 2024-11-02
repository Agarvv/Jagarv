import { Component, Input } from '@angular/core';
import { AuthService } from '../../../services/auth/auth.service';
import { FormGroup } from '@angular/forms'


@Component({
  selector: 'app-send-reset-password-button',
  templateUrl: './send-reset-password-button.component.html',
  styleUrl: './send-reset-password-button.component.css'
})
export class SendResetPasswordButtonComponent {
 @Input() form?: FormGroup
 
 constructor(private authService: AuthService) {
     
 }
 
 sendResetPasswordEmail() {
    if (!this.form || this.form.invalid) { 
        this.form?.markAllAsTouched(); 
        return;
    }
    
    this.authService.sendResetPassword(this.form.value).subscribe(
        (data) => {
            console.log("Check email", data);
        },
        (error) => {
            console.log("error while sending email", error);
        }
    );
}

 
}
