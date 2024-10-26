import { Component, Input } from '@angular/core';
import { ProductsService } from '../../../services/admin/products/products.service';


@Component({
  selector: 'app-feature-product-button',
  templateUrl: './feature-product-button.component.html',
  styleUrl: './feature-product-button.component.css'
})
export class FeatureProductButtonComponent {
 @Input() productId!: number; 
 constructor(private productsService: ProductsService) {}
 
 featureProduct() {
     this.productsService.featureProduct(this.productId).subscribe((data) => {
         console.log("Product feature OK", data)
     }, (error) => {
         console.log("Something went wrong while featuring product", error)
     })
 }
}
