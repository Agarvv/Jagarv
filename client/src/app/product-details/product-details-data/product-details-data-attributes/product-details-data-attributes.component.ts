import { Component, OnInit } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';

@Component({
  selector: 'app-product-details-data-attributes',
  templateUrl: './product-details-data-attributes.component.html',
  styleUrls: ['./product-details-data-attributes.component.css'] 
})
export class ProductDetailsDataAttributes {
    product$: Observable<Product | null>;
    
    constructor(private store: Store<ProductDetailsState>) {
        // just for now
        this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
      }


}