import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';
import { ProductSummary } from "@models/ProductSummary"


@Injectable({
  providedIn: 'root'
})
export class SearchService {
  private apiUrl = `${environment.apiUrl}/api/jagarv/search`; 

  constructor(private http: HttpClient) { }

  searchProducts(query: string): Observable<ProductSummary[]> {  
      return this.http.get<ProductSummary[]>(`${this.apiUrl}/${query}`);  
  }
  
}