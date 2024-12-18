import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AdminOrders } from '@models/admin/orders/AdminOrders';
import { Observable } from 'rxjs';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  private apiUrl = `${environment.apiUrl}/admin/orders`;  

  constructor(private http: HttpClient) { }
  
  // gets the orders from the server
  getOrders(): Observable<AdminOrders[]> {
     return this.http.get<AdminOrders[]>(this.apiUrl);
  }
  
  // set order status as arrivdd
  setOrderArrived(orderId: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/setArrived`, { orderId: orderId }); 
  }
  
  // set order status as in transit 
  setOrderInTransit(orderId: number): Observable<any> {
      return this.http.post(`${this.apiUrl}/setInProcess`, { orderId: orderId }); 
  }
}
