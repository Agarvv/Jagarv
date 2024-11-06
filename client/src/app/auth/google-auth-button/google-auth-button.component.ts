import { Component, OnInit } from '@angular/core';
import { environment } from '../../environments/environment';
import { AuthGoogleService } from '../../services/auth/social-auth/google/auth-google.service';
import { Store } from '@ngrx/store';
import { Router } from '@angular/router';
import { setError, setLoading, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs';

declare const google: any;

@Component({
  selector: 'app-google-auth-button',
  templateUrl: './google-auth-button.component.html',
  styleUrls: ['./google-auth-button.component.css']
})
export class GoogleAuthButtonComponent implements OnInit {

  constructor(private authGoogleService: AuthGoogleService, private store: Store, private router: Router) {}

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
    this.store.dispatch(clearMessages());
    this.store.dispatch(setLoading({ isLoading: true }));

    this.authGoogleService.sendGoogleTokenToServer(response.credential).pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false}))
      })
    ).subscribe((data) => {
      console.log("Google Auth Success (API)", data);
      this.router.navigate(['/']);
    }, (error) => {
      console.error("Google Auth Failure (API)", error);
      this.store.dispatch(setError({ errorMessage: "Oops, Something Went Wrong..."}));
    })


  }
}