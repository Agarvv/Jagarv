import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment'; 
import { Wishlist } from '@models/wishlist/Wishlist';
import { AddToWishlist } from '@models/wishlist/AddToWishlist';

@Injectable({
  providedIn: 'root'
})
export class WishlistService {

  private apiUrl = `${environment.apiUrl}/api/jagarv/wishlist`;

  constructor(private http: HttpClient) { }

  getUserWishlist(): Observable<Wishlist> {
      return this.http.get<Wishlist>(this.apiUrl, {
          withCredentials: true 
      }); 
  }

  addToWishlist(data: number): Observable<string> {
      return this.http.post<string>(`${this.apiUrl}/add`, data); 
  }
