import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { setCart } from '@store/cartt/cart.actions';  
import { CartService } from '@services/cart/cart.service';  
import { Cart } from '@models/cart/Cart'; 
import { Observable } from 'rxjs';
import { CartState } from '@store/cartt/cart.state';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cart$: Observable<Cart | null>;

  constructor(private cartService: CartService, private store: Store<{ cart: CartState }>) {
    this.cart$ = this.store.pipe(select(state => state.cart.cart));  
  }

  ngOnInit(): void {
    this.cartService.getUserCart().subscribe(
      (data) => {
        console.log('Cart service:', data);

        this.store.dispatch(setCart({ cart: data }));

        this.store.pipe(select(state => state.cart.cart)).subscribe(cart => {
          console.log('store cart', cart);
        });
      },
      (error) => {
        console.error('Erro:', error);
      }
    );
  }
}