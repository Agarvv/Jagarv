import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddToCart } from "@models/cart/AddToCart"
import { environment } from "@env/environment"

@Injectable({
  providedIn: 'root'
})
export class CartService {
    
  private apiUrl = `${environment.apiUrl}/api/jagarv/cart`

  constructor(private http: HttpClient) { }
  
  addOrRemoveToCart(data: any): Observable<any> 
  {
      return this.http.post(`${this.apiUrl}/addOrRemove`, data);
  }
  
  
}
