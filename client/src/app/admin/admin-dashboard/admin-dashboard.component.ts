import { Component, OnInit } from '@angular/core';
import { DashboardService } from '@services/admin/dashboard/dashboard.service';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'] 
})
export class AdminDashboardComponent implements OnInit {
  dashboardData$: Observable<AdminDashboard> | null = null; 

  constructor(private dashboardService: DashboardService) {}

  ngOnInit() {
    this.dashboardData$ = this.dashboardService.getAdminDashboard(); 
  }
}