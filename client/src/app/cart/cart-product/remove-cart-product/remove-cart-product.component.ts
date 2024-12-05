import { Component, Input } from '@angular/core';
import { CartService } from '@services/cart/cart.service'
import { Store } from '@ngrx/store'
import { clearMessages, setError } from '@store/admin/admin.actions'


@Component({
  selector: 'app-remove-cart-product',
  templateUrl: './remove-cart-product.component.html',
  styleUrl: './remove-cart-product.component.css'
})
export class RemoveCartProductComponent {
 @Input() productId: number | null = null;
 
 constructor(private cartService: CartService, private store: Store) {} 
 
  removeProductFromCart(): void {
      this.store.dispatch(clearMessages()); 
     if(this.productId) {
         this.cartService.addOrRemoveToCart('remove', this.productId)
         .subscribe((data) => {
             console.log('Data from cart', data);
             window.location.reload(); // simple
         }, (error) => {
             console.error(error)
             this.store.dispatch(setError({
                 errorMessage: 'Something went wrong...'
             }))
         })
     } else {
         console.error('Product id not provided')
     }
  }
  
  ngOnChanges() {
  console.log('product id rec:', this.productId);
}
}
