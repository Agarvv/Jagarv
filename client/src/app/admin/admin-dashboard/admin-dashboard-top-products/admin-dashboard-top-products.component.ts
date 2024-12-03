import { Component, Input } from '@angular/core';

import { AdminDashboard } from '@models/admin/dashboard/AdminDashboard'

@Component({
  selector: 'app-admin-dashboard-top-products',
  templateUrl: './admin-dashboard-top-products.component.html',
  styleUrl: './admin-dashboard-top-products.component.css'
})
export class AdminDashboardTopProductsComponent {
 @Input() dashboard: AdminDashboard | null = null; 
}
