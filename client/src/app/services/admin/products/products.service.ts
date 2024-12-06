import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from '../../../models/Product';

@Injectable({
  providedIn: 'root'
})
// this service will handle CRUD admin logic.
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
 
 // deletes a product by his id
  deleteProduct(productId: number): Observable<number> {
  return this.http.delete<number>(`${this.apiUrl}/delete/${productId}`);
  }
  
  // updates a product
  updateProduct(productId: number, productData: Product): Observable<Product> {
    return this.http.put<Product>(`${this.apiUrl}/update/${productId}`, productData);
  }
  
  // features a product 
  featureProduct(productId: number): Observable<number> {
      return this.http.post<number>(`${this.apiUrl}/feature`, { productId: productId });
  }
  
  // adds product variant, will be typed soon.
  addProductVariant(data: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/variants/create`, data)
  }

}