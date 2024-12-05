import { Component, Input } from '@angular/core';

import { Product } from '@models/Product'

@Component({
  selector: 'app-cart-product',
  templateUrl: './cart-product.component.html',
  styleUrl: './cart-product.component.css'
})
export class CartProductComponent {
  @Input() product: Product | null = null; 
  
}
