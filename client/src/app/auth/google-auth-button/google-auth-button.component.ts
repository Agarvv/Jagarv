import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';

declare const google: any;

@Component({
  selector: 'app-google-auth-button',
  templateUrl: './google-auth-button.component.html',
  styleUrls: ['./google-auth-button.component.css']
})
export class GoogleAuthButtonComponent implements OnInit {

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
    // here we will send it to the server 
  }
}