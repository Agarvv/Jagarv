import { Component, Input } from '@angular/core';

import { Product } from '@models/Product'
import { CartItem } from '@models/cart/CartItem'


@Component({
  selector: 'app-cart-product',
  templateUrl: './cart-product.component.html',
  styleUrl: './cart-product.component.css'
})
export class CartProductComponent {
  @Input() product: CartItem | null = null; 
  
}
