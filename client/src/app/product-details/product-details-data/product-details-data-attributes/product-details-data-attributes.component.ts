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
    attributes$: Observable<{ attributeName: string; attributeOptionId: number }[]>; // debug

    constructor(private store: Store<ProductDetailsState>) {
        this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
        this.attributes$ = this.store.pipe(select((state: any) => state.productDetails.attributes));
    
        // debug
        this.attributes$.subscribe(attributes => {
          console.log('attribute', attributes);
        });
    }
    
    onOptionChange(event: any, attributeName: string) {
        console.log(event.target.value)
        console.log('changed', attributeName);
        const finalAttributeObject = 
        {
           attributeName: attributeName,
           attributeOptionId: Number(event.target.value)
        }
        console.log('final attribute object', finalAttributeObject)
        this.store.dispatch(addOrUpdateAttribute({ attribute: finalAttributeObject }));
}

}

