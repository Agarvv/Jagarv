import { Component, Input } from '@angular/core';
import { ProductSummary } from "@models/ProductSummary"



@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.css'
})
export class ProductCardComponent {
// @Input() product!: ProductSummary; 
@Input() product!: any;  // will be used more times in the app with diferent data, that's why any.
 
 
}
