import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard-chart',
  templateUrl: './admin-dashboard-chart.component.html',
  styleUrl: './admin-dashboard-chart.component.css'
})
export class AdminDashboardChartComponent {
 @Input() dashboard: AdminDashboard | null = null; 
}
