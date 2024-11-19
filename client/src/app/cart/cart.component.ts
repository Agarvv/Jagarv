import { Component, OnInit } from '@angular/core';
import { CartService } from '@services/cart/cart.service'; 
import { Store } from 'ngrx/store'
import { setError, clearMessages } from '@store/admin/admin.actions'; 

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {
  constructor(private cartService: CartService, private store: Store) {} 
  
  ngOnInit(): void {
      this.store.dispatch(clearMessages()); 
      this.cartService.getUserCart() 
      .subscribe((data) => {
          console.log('User cart', data)
      }, (error) => {
          console.error(error); 
          this.store.dispatch(setError({
              errorMessage: 'Oops, Something Went Wrong...'
          }))
      })
  }
}
