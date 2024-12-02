import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs'
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class PaypalService {
  private apiUrl = `${environment.apiUrl}/api/jagarv/pay/paypal`;
  constructor(private http: HttpClient) { }

  payWithPaypal(): Observable<any> {
    return this.http.post(this.apiUrl, {}, {
        withCredentials: true 
    });
  }
}
