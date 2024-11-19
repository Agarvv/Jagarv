import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { Product } from "@models/Product"


@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private apiUrl = `${environment.apiUrl}/api/jagarv/search`; 

  constructor(private http: HttpClient) { }

  searchProducts(query: string): Observable<Product[]> {  
      return this.http.get<Product[]>(`${this.apiUrl}/${query}`);  
  }
  
}