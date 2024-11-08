import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Sales } from '@models/Sales/Sales';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root'
})
export class SalesService {
  private apiUrl = `${environment.apiUrl}/admin/sales`
  constructor(private http: HttpClient) { }

  getSales(): Observable<Sales[]> {
    return this.http.get<Sales[]>(`${environment.apiUrl}`);
  }


}
