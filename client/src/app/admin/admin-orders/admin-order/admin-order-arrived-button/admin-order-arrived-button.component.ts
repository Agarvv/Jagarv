import { Component, Input } from '@angular/core';
import { OrdersService } from '@services/admin/orders/orders.service'
import { Store } from '@ngrx/store'
import { setError, clearMessages } from '@store/admin/admin.actions'


@Component({
  selector: 'app-admin-order-arrived-button',
  templateUrl: './admin-order-arrived-button.component.html',
  styleUrl: './admin-order-arrived-button.component.css'
})
export class AdminOrderArrivedButtonComponent {
  @Input() orderId: number | null = null; 
  
  constructor(private ordersService: OrdersService, private store: Store) {} 
  
  setOrderArrived(): void {
      this.store.dispatch(clearMessages()); 
      this.ordersService.setOrderArrived(this.orderId)
      .subscribe((data) => {
          console.log("Data from order arrived", data); 
          window.location.reload()
      }, (error) => {
          console.error(error)
          this.store.dispatch(setError({
              errorMessage: "Could not set order status as arrived..."
          }))
      })
  }
}
