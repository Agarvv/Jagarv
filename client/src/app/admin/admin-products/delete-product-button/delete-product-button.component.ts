import { Component, Input } from '@angular/core';
import { ProductsService } from '../../../services/admin/products/products.service';


@Component({
  selector: 'app-delete-product-button',
  templateUrl: './delete-product-button.component.html',
  styleUrl: './delete-product-button.component.css'
})
// this component just deletes a product by his id
export class DeleteProductButtonComponent {
  @Input() productId!: number; 
  constructor(private productsService: ProductsService) {}
  
  deleteProduct() { // i dont know if this is going to return something so i dont make it a void... for now.
    this.productsService.deleteProduct(this.productId).subscribe((data) => {
      console.log('Product Deleted!', data);
    }, (error) => {
      console.error('Error deleting product!', error);
    })
  }
}
