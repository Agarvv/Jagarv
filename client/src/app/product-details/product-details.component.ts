import { Component, OnInit } from '@angular/core';
import { PublicProductsService } from '@services/products/products.service';
import { Product } from "@models/Product"
import { ActivatedRoute } from '@angular/router'; 
import { Store } from '@ngrx/store';
import { setError, clearMessages } from "@store/admin/admin.actions"

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
// this component gets the product wanted
export class ProductDetailsComponent implements OnInit {
   product: Product | undefined; // this will hold the product details

   constructor(
    private productsService: PublicProductsService,
    private route: ActivatedRoute,
    private store: Store
    ) {}

    ngOnInit() {
      this.getProduct(); // when the component is initialized, it gets the product details
    }
   
    // gets the product and sets it
   getProduct(): void {
    this.store.dispatch(clearMessages());
    const productId = this.route.snapshot.paramMap.get('productId')
     this.productsService.getProductById(Number(productId)).subscribe((product: Product) => {
       this.product = product;
       console.log('product received', product) // debug
     }, (error) => {
      console.error('error', error) // debug for developers
        this.store.dispatch(setError({ errorMessage: "Something went wrong. Please Try again later" }));
         // quick error mesage
     });
   }
}
