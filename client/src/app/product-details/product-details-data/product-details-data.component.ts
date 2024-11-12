import { Component } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';

@Component({
  selector: 'app-product-details-data',
  templateUrl: './product-details-data.component.html',
  styleUrls: ['./product-details-data.component.css']
})
export class ProductDetailsDataComponent {
  product$: Observable<Product | null>;

  constructor(private store: Store<ProductDetailsState>) {
    this.product$ = this.store.pipe(select(state => state.productDetails.product));
  }

}