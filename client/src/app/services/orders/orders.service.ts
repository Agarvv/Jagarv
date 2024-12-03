import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '@models/orders/Orders';
import { Observable } from 'rxjs'
import { environment } from '@env/environment';



@Injectable({
  providedIn: 'root'
})
export class OrdersService {
  private apiUrl = `${environment.apiUrl}/api/jagarv/orders`
  constructor(private http: HttpClient) { }
  
   getUserOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl);
   }
}
