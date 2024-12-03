import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard-insights',
  templateUrl: './admin-dashboard-insights.component.html',
  styleUrl: './admin-dashboard-insights.component.css'
})
export class AdminDashboardInsightsComponent {
  @Input() totalEarningsToday!: number;
  @Input() ordersToday!: number;
  @Input() usersToday!: number;
}
