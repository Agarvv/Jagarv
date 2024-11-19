// cart.component.ts

import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { setCart } from '@store/cartt/cart.actions'; 
import { CartService } from '@services/cart/cart.service'; 
import { Cart } from '@models/cart/Cart'; 
import { Observable } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cart$: Observable<Cart | null>;

  constructor(private cartService: CartService, private store: Store) {
    this.cart$ = this.store.pipe(select(state => state.cart.cart));
  }

  ngOnInit(): void {
    this.cartService.getUserCart().subscribe(
      (data) => {
        this.store.dispatch(setCart({ cart: data }));
        console.log('final cart', cart)
      },
      (error) => {
        console.error(error);  
      }
    );
  }
}