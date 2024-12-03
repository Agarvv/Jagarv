import { Component, Input } from '@angular/core';

import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrl: './admin-dashboard-chart.component.css'
})
export class AdminDashboardChartComponent {
 @Input() dashboard: AdminDashboard | null = null; 
}
