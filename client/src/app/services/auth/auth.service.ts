import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { RegisterUser } from '../../models/User/RegisterUser';
import { LoginUser } from '../../models/User/LoginUser';
import { environment } from '../../environments/environment'; 
import { map, catchError, }from "rxjs/operators"

@Injectable({
  providedIn: 'root'
})
// this service makes requests to server's auth endpoints.
export class AuthService {
    
  private apiUrl = `${environment.apiUrl}/api/jagarv/auth`;
   
  constructor(private http: HttpClient) {}
  
  // this doesn't need explication, just registers a user.
  registerUser(user: RegisterUser): Observable<any> {
    return this.http.post(`${this.apiUrl}/register`, user);
  }
  
  // logins a user 
  loginUser(user: LoginUser): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, user, {
        withCredentials: true 
    });
  }

  // sends reset password url with instructions to reset password
  sendResetPassword(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/send_reset_code`, data);
  }
  
  // resets password 
  resetPassword(data: any): Observable<any> {
    return this.http.post(`${this.apiUrl}/reset_password`, { data });
  }
  
  // check if the user is authenticated
  isAuthenticated(): Observable<boolean> {
      return this.http.get(`${this.apiUrl}/check`, {
          withCredentials: true 
      })
      .pipe(
     map(() => true),
     catchError(() => of(false))
     
     )
  }
}