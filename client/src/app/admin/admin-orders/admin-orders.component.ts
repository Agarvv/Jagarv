import { AdminOrders } from '@models/admin/orders/AdminOrders';
import { Component, OnInit } from '@angular/core';
import { OrdersService } from '@services/admin/orders/orders.service';
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrl: './admin-orders.component.css'
})
export class AdminOrdersComponent implements OnInit {
  orders: AdminOrders[] = [];

  constructor(private ordersService: OrdersService, private store: Store) {}
  
  ngOnInit(): void {
      this.getOrders();
  }

  getOrders(): void {
    this.store.dispatch(clearMessages());
    this.ordersService.getOrders().pipe(
      // will be aded here something in the future if necesary
    ).subscribe((data: AdminOrders[]) => {
      console.log("Orders fetched successfully", data);
        this.orders = data;
    }, (error) => {
      this.store.dispatch(setError({ errorMessage: "Somthing went wrong, please try again" }));
    })
  }
}
