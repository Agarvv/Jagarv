import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthGoogleService } from '../../services/auth/social-auth/google/auth-google.service';

declare const google: any;

@Component({
  selector: 'app-google-auth-button',
  templateUrl: './google-auth-button.component.html',
  styleUrls: ['./google-auth-button.component.css']
})
export class GoogleAuthButtonComponent implements OnInit {

  constructor(private authGoogleService: AuthGoogleService) {}

  ngOnInit(): void {
    google.accounts.id.initialize({
      client_id: environment.googleClientId,
      callback: (response: any) => this.handleCredentialResponse(response)
    });

    google.accounts.id.renderButton(
      document.getElementById("buttonDiv"),
      { theme: "outline", size: "large" }
    );
  }

  handleCredentialResponse(response: any) {
    console.log("Google Auth Success", response.credential);
    this.authGoogleService.sendGoogleTokenToServer(response.credential).subscribe((data) => {
      console.log("Google Auth Success", data);
    }, (error) => {
      console.error("Google Auth Failure", error);
    })
  }
}