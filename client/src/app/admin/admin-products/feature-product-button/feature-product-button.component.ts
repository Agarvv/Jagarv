import { Component, Input } from '@angular/core';
import { ProductsService } from '../../../services/admin/products/products.service';
import { Store } from '@ngrx/store'
import { setError, clearMessages } from '@store/admin/admin.actions'

@Component({
  selector: 'app-feature-product-button',
  templateUrl: './feature-product-button.component.html',
  styleUrl: './feature-product-button.component.css'
})
// this component just features, or unfeatures a product by his ID.
export class FeatureProductButtonComponent {
 @Input() productId!: number; 
 constructor(private productsService: ProductsService, private store: Store) {}
 
 featureProduct() {
     this.store.dispatch(clearMessages()); 
     this.productsService.featureProduct(this.productId).subscribe((data) => {
         console.log("Product feature OK", data)
         window.location.reload()
     }, (error) => {
         this.store.dispatch(setError({
             errorMessage: 'Ooops, Something went wrong...'
         }))
     })
 }
}
