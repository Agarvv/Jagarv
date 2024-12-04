import { Component, OnInit } from '@angular/core';
import { OrdersService } from '@services/orders/orders.service';
import { Order } from '@models/orders/Orders';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  constructor(private ordersService: OrdersService) {}

  ngOnInit(): void {
    this.ordersService.getUserOrders()
    .pipe(

    )
    .subscribe(orders => {
      this.orders = orders;
      console.log("orders received", orders)
    }, (error) => {
      console.error("something went wrong", error); // debug
    });
  }

}
