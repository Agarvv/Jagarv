import { Component, OnInit } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';
import { tap } from 'rxjs/operators';  // No olvides importar tap

@Component({
  selector: 'app-product-details-data',
  templateUrl: './product-details-data.component.html',
  styleUrls: ['./product-details-data.component.css']
})
export class ProductDetailsDataComponent implements OnInit {
  product$: Observable<Product | null>;

  constructor(private store: Store<ProductDetailsState>) {

    this.product$ = this.store.pipe(
      select(state => state.product),  
      tap(product => console.log('Product updated', product)) 
    );
  }

}