import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment'; 



@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.apiUrl; 

  constructor(private http: HttpClient) {}

  testApi() {
    return this.http.get<any>(`${this.apiUrl}/health`);  
  }
}
