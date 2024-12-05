import { Component, Input, OnInit } from '@angular/core';

import { Product } from '@models/Product'

@Component({
  selector: 'app-cart-product',
  templateUrl: './cart-product.component.html',
  styleUrl: './cart-product.component.css'
})
export class CartProductComponent implements OnInit {
  @Input() product: Product | null = null; 
  
  ngOnInit(): void {
      console.log("product propc art", this.product)
  }
}
