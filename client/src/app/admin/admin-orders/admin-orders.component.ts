import { Component, OnInit } from '@angular/core';
import { OrdersService } from '@services/admin/orders/orders.service';
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions';
import { Orders } from '@models/orders/Orders';

@Component({
  selector: 'app-admin-orders',
  templateUrl: './admin-orders.component.html',
  styleUrl: './admin-orders.component.css'
})
export class AdminOrdersComponent implements OnInit {
  orders: Orders[] = [];

  constructor(private ordersService: OrdersService, private store: Store) {}
  
  ngOnInit(): void {
      this.getOrders();
  }

  getOrders(): void {
    this.store.dispatch(clearMessages());
    this.ordersService.getOrders().pipe(
      // will be aded here something in the future if necesary
    ).subscribe((data: Orders[]) => {
      console.log("Orders fetched successfully", data);
        this.orders = data;
    }, (error) => {
      this.store.dispatch(setError({ errorMessage: "Somthing went wrong, please try again" }));
    })
  }
}
