import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Orders } from '@models/orders/Orders';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  private apiUrl = `${environment.apiUrl}/admin/orders`;  

  constructor(private http: HttpClient) { }
  
  // gets the orders from the server
  getOrders(): Observable<Orders[]> {
     return this.http.get<Orders[]>(this.apiUrl);
  }
}
