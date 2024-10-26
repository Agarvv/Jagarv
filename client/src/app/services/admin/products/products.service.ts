import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../../../models/Product';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
  private apiUrl = `${environment.apiUrl}/admin/products`;

  constructor(private http: HttpClient) { }
  
  // Gets all the products from the server 
  getProducts(): Observable<Product[]> { 
      return this.http.get<Product[]>(this.apiUrl);
  }
  
  createProduct(product: Product): Observable<Product> { 
    console.log('creating product from service', product);
    return this.http.post<Product>(`${this.apiUrl}/create`, product);
  }
 
 // deletes a post by his id
  deleteProduct(productId: number): Observable<number> {
  return this.http.delete<number>(`${this.apiUrl}/delete/${productId}`);
}
}