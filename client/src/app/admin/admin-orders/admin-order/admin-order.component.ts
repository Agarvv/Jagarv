import { Component, Input } from '@angular/core';
import { AdminOrders } from '@models/admin/orders/AdminOrders';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrl: './admin-order.component.css'
})
export class AdminOrderComponent {
  @Input() order: AdminOrders | null = null;
}
