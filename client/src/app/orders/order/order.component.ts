import { Component, Input } from '@angular/core';
import { Order } from '@models/orders/Orders';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrl: './order.component.css'
})
export class OrderComponent {
 @Input() order: Order | null = null;
}
