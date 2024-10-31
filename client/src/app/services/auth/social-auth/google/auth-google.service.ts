import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'; 

@Injectable({
  providedIn: 'root'
})
export class AuthGoogleService {
  constructor(private http: HttpClient) { }
  
  private apiUrl = "im gonna import environment apiUrl later"
  
  sendGoogleTokenToServer(token: string): Observable<any> {
      return this.http.post<any>(`${apiUrl}/google`, token); 
  }
}
