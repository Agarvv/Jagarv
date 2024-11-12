import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment';
import { Observable } from 'rxjs';
import { CreateProductOpinion } from '@models/CreateProductOpinion';

@Injectable({
  providedIn: 'root'
})
// handles product's opinions http logic
export class OpinionsService {
  private apiUrl = `${environment.apiUrl}/api/jagarv/products/opinions`

  constructor(private http: HttpClient) { }
  
  // creates a new opinion for a product
  createOpinion(opinion: CreateProductOpinion): Observable<CreateProductOpinion> {
    return this.http.post<CreateProductOpinion>(`${this.apiUrl}/create`, opinion);
  }
}
