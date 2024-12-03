import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@env/environment'
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'


@Injectable({
  providedIn: 'root'
})
export class DashboardService {
  private apiUrl = `${environment.apiUrl}/admin/dashboard`
  constructor(private http: HttpClient) { }
  
  getAdminDashboard(): Observable<AdminDashboard> {
      return this.http.get<AdminDashboard>(apiUrl);
  }
}
