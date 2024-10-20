import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductsService {
    
   private apiUrl = `${environment.apiUrl}/admin/products`;

  constructor(private http: HttpClient) { }
  
  // Sends a Request to the server to create a new Product
  createProduct(product: any): Observable<any> {
      return this.http.post<any>(`${this.apiUrl}/create`, product);
  }
  
  
}
