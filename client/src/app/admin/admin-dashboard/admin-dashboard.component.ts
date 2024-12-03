import { Component, OnInit } from '@angular/core';
import { DashboardService } from '@services/admin/dashboard/dashboard.service'
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {
  dashboardData: AdminDashboard | null = null; 
  constructor(private dashboardService: DashboardService) {} 
  
  ngOnInit() {
      this.dashboardService.getAdminDashboard()
      .pipe(
      
      )
      .subscribe((data: AdminDashboard) => {
          console.log("Dashboard", dashboard)
          this.dashboardData = data; 
      }, (error) => {
          console.error("Error!", error) // debug8
      }) 
  }
}
