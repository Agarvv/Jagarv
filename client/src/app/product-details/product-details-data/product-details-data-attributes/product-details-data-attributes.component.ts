import { Component } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';
import { Attribute } from "@models/Attribute"

@Component({
  selector: 'app-product-details-data-attributes',
  templateUrl: './product-details-data-attributes.component.html',
  styleUrl: './product-details-data-attributes.component.css'
})
// This product handles all of our product attributes logic,
// like setting attributes for the product that we are going to buy
export class ProductDetailsDataAttributesComponent {
  product$: Observable<Product | null>;
  
  constructor(private store: Store<ProductDetailsState>) {
    // just for now
    this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
  }
  
  // this will handle when a product attribute changes,
  // showing the correct product variant to store if necesary
  onOptionChange(event: any) 
  {
      console.log('Select changed', event.target.value);
      
  }
}
