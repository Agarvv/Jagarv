import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddToCart } from "@models/cart/AddToCart"
import { environment } from "@env/environment"

@Injectable({
  providedIn: 'root'
})
export class CartService {
    
  private apiUrl = `${apiUrl}/api/jagarv/cart`

  constructor(private http: HttpClient) { }
  
  addToCard(data: AddToCart): Observable<AddToCart> 
  {
      return this.http.post(`${apiUrl}/create`, data);
  }
  
  
}
