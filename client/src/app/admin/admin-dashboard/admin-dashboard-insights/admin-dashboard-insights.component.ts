import { Component, Input, OnInit } from '@angular/core';
import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'


@Component({
  selector: 'app-admin-dashboard-insights',
  templateUrl: './admin-dashboard-insights.component.html',
  styleUrl: './admin-dashboard-insights.component.css'
})
export class AdminDashboardInsightsComponent implements OnInit {
  @Input() dashboard: AdminDashboard | null = null;
  
  ngOnInit(): void {
      console.log("Dashboard prop", this.dashboard)
  }
}
