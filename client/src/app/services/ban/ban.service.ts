import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment'
import { Observable } from 'rxjs'

@Injectable({
  providedIn: 'root'
})
export class BanService {
    private apiUrl = `${environment.apiUrl}/admin/user/ban`

  constructor(private http: HttpClient) { }
  
  banOrUnbanUser(userId: number): Observable<string> {
      return this.http.post<string>(this.apiUrl, { userId: userId });
  }
}
