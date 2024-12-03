import { Component, Input } from '@angular/core';
import { Order } from '@stripe/stripe-js';

@Component({
  selector: 'app-admin-order',
  templateUrl: './admin-order.component.html',
  styleUrl: './admin-order.component.css'
})
export class AdminOrderComponent {
  @Input() order: Order | null = null;
}
