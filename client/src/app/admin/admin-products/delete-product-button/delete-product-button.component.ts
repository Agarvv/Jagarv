import { Component, Input } from '@angular/core';
import { ProductsService } from '../../../services/admin/products/products.service';

@Component({
  selector: 'app-delete-product-button',
  templateUrl: './delete-product-button.component.html',
  styleUrls: ['./delete-product-button.component.css']
})
export class DeleteProductButtonComponent {
  @Input() productId!: number; 

  constructor(
      private productsService: ProductsService
  ) {}

// just deletes a product by the id passed as prop
  deleteProduct() {
    this.productsService.deleteProduct(this.productId).subscribe(
      () => {
        console.log('Product Deleted!');
        window.location.reload()
      },
      (error) => {
        console.error('Error deleting product!', error);
      }
    );
  }
}