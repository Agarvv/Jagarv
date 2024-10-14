import { Injectable } from '@angular/core';




@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = environment.apiUrl; 

  constructor() {}


}
