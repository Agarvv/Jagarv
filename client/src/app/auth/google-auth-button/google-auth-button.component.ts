import { Component } from '@angular/core';
import { SocialAuthService, GoogleLoginProvider } from '@abacritt/angularx-social-login';

@Component({
  selector: 'app-google-auth-button',
  templateUrl: './google-auth-button.component.html',
  styleUrls: ['./google-auth-button.component.css' ]  
})
export class GoogleAuthButtonComponent {
   constructor(private googleAuthService: SocialAuthService) {}  
   
   loginWithGoogle(): void {
       this.googleAuthService.signIn(GoogleLoginProvider.PROVIDER_ID).then((user: any) => {
           console.log("Google Auth Success", user.idToken);
           
       }).catch((error: any) => {
           console.log('Google Auth Failure', error);
       });
   }
}