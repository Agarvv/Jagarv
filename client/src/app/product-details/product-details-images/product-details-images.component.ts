import { Component } from '@angular/core';
import { Product } from '@models/Product';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';

@Component({
  selector: 'app-product-details-images',
  templateUrl: './product-details-images.component.html',
  styleUrl: './product-details-images.component.css'
})
export class ProductDetailsImagesComponent {
   product$: Observable<Product | null>;
   imageIndex: number = 0;
   product: Product | null = null;

   constructor(private store: Store<ProductDetailsState>) {
     this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
     
     this.product$.subscribe(product => {
       this.product = product; 
     });
   }

   changeImage(index: number): void {
       console.log('Image new index', index);
       this.imageIndex = index; 
   }
   
   decrementImage(): void {
       if (this.product && this.product.images && this.imageIndex > 0) {
           this.imageIndex--;
       } else if (this.product && this.product.images) {
           this.imageIndex = this.product.images.length - 1; 
       }
   }
   
   incrementImage(): void {
       if (this.product && this.product.images && this.imageIndex < this.product.images.length - 1) {
           this.imageIndex++;
       } else if (this.product && this.product.images) {
           this.imageIndex =  0;
       }
   }
}