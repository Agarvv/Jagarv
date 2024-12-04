import { Component, Input } from '@angular/core';
import { OrdersService } from '@services/admin/orders/orders.service'
import { Store } from '@ngrx/store'
import { setError, clearMessages } from '@store/admin/admin.actions'


@Component({
  selector: 'app-admin-order-in-transit-button',
  templateUrl: './admin-order-in-transit-button.component.html',
  styleUrl: './admin-order-in-transit-button.component.css'
})
export class AdminOrderInTransitButtonComponent {
  @Input() orderId: number | null = null; 
  
  constructor(private ordersService: OrdersService, private store: Store) {} 
  
  setOrderInTransit() {
      this.ordersService.setOrderInTransit(this.orderId)
      .subscribe((data) => {
          window.location.reload()
      }, (error) => {
          console.error(error)
          this.store.dispatch(setError({
              errorMessage: 'Could not set order status in transit...'
          }))
      })
  }
}
