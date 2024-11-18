import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { Observable } from 'rxjs';
import { User } from '@models/user/User'; 

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  private apiUrl = `${environment.apiUrl}/api/jagarv/user`; 
  constructor(private http: HttpClient) { }
  
  // finds user data 
  getUserData(): Observable<User> {
      return this.http.get<User>(apiUrl, {
          withCredentials: true 
      })
  }
  
  // here data is the URL of the profile picture, a string 
  setUserPicture(data: string): Observable<string>
  {
      return this.http.post(`${apiUrl}/setProfilePicture`, data, {
          withCredentials: true 
      })
  }
}
