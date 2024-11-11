import { Component, Input } from '@angular/core';
import { Product } from '@models/Product';

@Component({
  selector: 'app-product-details-data',
  templateUrl: './product-details-data.component.html',
  styleUrl: './product-details-data.component.css'
})
export class ProductDetailsDataComponent {
 @Input() product: Product | undefined;
}
