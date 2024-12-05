import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AddToCart } from "@models/cart/AddToCart";
import { environment } from "@env/environment";
import { Cart } from '@models/cart/Cart'; 


@Injectable({
  providedIn: 'root'
})
export class CartService {
    
  private apiUrl = `${environment.apiUrl}/api/jagarv/cart`
  

  constructor(private http: HttpClient) { }
  
  getUserCart(): Observable<Cart> {
      return this.http.get<Cart>(this.apiUrl, {
          withCredentials: true 
      })
  }
  
  addOrRemoveToCart(action: 'add' | 'remove', data: any): Observable<any> {
    const payload = action === 'add' 
        ? { productId: data.productId, options: data.options, quantity: data.quantity }
        : { productId: data, quantity: 1, options: [] };

    return this.http.post(`${this.apiUrl}/addOrRemove`, payload, {
        withCredentials: true
    });
 }
  
  calculateCartFinalPrice(cart: Cart): number {
  const finalPrice = cart.items.reduce((total, product) => {
      
    return total + product.price;  
    
  }, 0);  
  
  return finalPrice;
  
  } 


}
