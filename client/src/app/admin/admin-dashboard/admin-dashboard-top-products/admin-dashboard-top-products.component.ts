import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-admin-dashboard-top-products',
  templateUrl: './admin-dashboard-top-products.component.html',
  styleUrl: './admin-dashboard-top-products.component.css'
})
export class AdminDashboardTopProductsComponent {
 @Input() mostOrderedProducts!: [number, string, number][];
}
