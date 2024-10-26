import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment'; 
import { Observable } from 'rxjs';
import { Product } from '../../models/Product'; 

@Injectable({
  providedIn: 'root'
})
// this service will handle all the public products logic, no admin products logic here.
export class PublicProductsService {
   
  private apiUrl = `${environment.apiUrl}/products`;
  constructor(private http: HttpClient) { }
  
  getProductById(productId: number): Observable<Product> {
    return this.http.get<Product>(`${this.apiUrl}/${productId}`) 
  }
}
