import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { Observable } from 'rxjs';
import { Product } from '@models/Product'; 
import { ProductSummary } from "@models/ProductSummary"


@Injectable({
  providedIn: 'root'
})
// this service will handle all the public products logic, no admin products logic here.
export class PublicProductsService {
   
  private apiUrl = `${environment.apiUrl}/api/jagarv/products`;
  constructor(private http: HttpClient) { }
  
  // finds product by id 
  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${productId}`, {
        withCredentials: true 
    }) 
  }
  
  // finds product by category 
  findProductsByCategory(category: string): Observable<ProductSummary[]> {
  return this.http.get<ProductSummary[]>(`${this.apiUrl}/category/${category}`);
}
  
}
