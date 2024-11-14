import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { environment } from '../../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthGoogleService {
  constructor(private http: HttpClient) { }

  private apiUrl = environment.apiUrl + '/api/jagarv/auth';
  
  sendGoogleTokenToServer(token: string): Observable<any> {
      return this.http.post<any>(`${this.apiUrl}/google`, token, {
          withCredentials: true
      }); 
  }
}
