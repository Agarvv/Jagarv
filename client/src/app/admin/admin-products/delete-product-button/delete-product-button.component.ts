import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ProductsService } from '../../../services/admin/products/products.service';

@Component({
  selector: 'app-delete-product-button',
  templateUrl: './delete-product-button.component.html',
  styleUrls: ['./delete-product-button.component.css']
})
export class DeleteProductButtonComponent {
  @Input() productId!: number; 
  @Output() productDeleted = new EventEmitter<number>();

  constructor(
      private productsService: ProductsService
  ) {}

// just deletes a product by the id passed as prop
  deleteProduct() {
    this.productsService.deleteProduct(this.productId).subscribe(
      () => {
        console.log('Product Deleted!');
        // emits a event to the father compponent,
        // the father component will delete from the UI the product with this id
        this.productDeleted.emit(this.productId); 
      },
      (error) => {
        console.error('Error deleting product!', error);
      }
    );
  }
}