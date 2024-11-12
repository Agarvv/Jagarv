import { Component, OnInit } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';
import { addOrUpdateAttribute } from '@store/cart/product-details.actions'

@Component({
  selector: 'app-product-details-data-attributes',
  templateUrl: './product-details-data-attributes.component.html',
  styleUrls: ['./product-details-data-attributes.component.css'] 
})
export class ProductDetailsDataAttributesComponent {
    product$: Observable<Product | null>;

    constructor(private store: Store<ProductDetailsState>) {
        this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
    }
    
    onOptionChange(event: any, attributeName: string) {
        const finalAttributeObject = 
        {
           attributeName: attributeName,
           attributeOptionId: Number(event.target.value)
        }
        this.store.dispatch(addOrUpdateAttribute({ attribute: finalAttributeObject }));
}

}

