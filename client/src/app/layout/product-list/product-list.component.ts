import { Component, Input } from '@angular/core';
import { Product } from "@models/Product"


@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrl: './product-list.component.css'
})
export class ProductListComponent {
  // @Input() products: Product[] | null = [];
  @Input() products: any[] | null = []; // will be used more times in the app with diferent data, that's why any.
 
}
