import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegisterUser } from '@models/User/RegisterUser';
import { environment } from '@env/environment';

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
  
  
  
  
}