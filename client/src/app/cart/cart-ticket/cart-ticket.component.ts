import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { Cart } from '@models/cart/Cart';
import { CartState } from '@store/cartt/cart.state';
import { CartService } from '@services/cart/cart.service';  

@Component({
  selector: 'app-cart-ticket',
  templateUrl: './cart-ticket.component.html',
  styleUrl: './cart-ticket.component.css'
})
export class CartTicketComponent implements OnInit {
  cart$: Observable<Cart | null>;
  finalPrice: number | null = null;

  constructor(private cartService: CartService, private store: Store<{ cart: CartState }>) {
    this.cart$ = this.store.pipe(select(state => state.cart.cart));
  }

  ngOnInit(): void {
    this.cart$.subscribe((cart) => {
      if (cart) {
        this.finalPrice = this.cartService.calculateCartFinalPrice(cart);
        console.log('Cart final Price: ', this.finalPrice);
      }
    });
  }
}