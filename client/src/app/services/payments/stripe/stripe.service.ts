import { Injectable } from '@angular/core';
import { loadStripe, Stripe, StripeElements } from '@stripe/stripe-js';
import { environment } from '@env/environment';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class StripeService {
 private apiUrl = `${environment.apiUrl}/api/jagarv/pay/stripe`;

  constructor(private http: HttpClient) {}

  createCheckoutSession(): Observable<any> {
    return this.http.post<any>(this.apiUrl, {}, {
      withCredentials: true
    });
  }

  
}
