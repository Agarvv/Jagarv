import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../../services/admin/products/products-service';
import { finalize } from 'rxjs/operators';
import { Product } from '../../models/Product'

@Component({
  selector: 'app-admin-products',
  templateUrl: './admin-products.component.html',
  styleUrl: ['./admin-products.component.css'] 
})
export class AdminProductsComponent implements OnInit {
  products: Product[] = []; 
  
  constructor(private productsService: ProductsService) {}

  ngOnInit() {
    this.loadProducts();
  }

  loadProducts() {
    
    this.productsService.getProducts()
      .pipe(
        finalize(() => ) 
      )
      .subscribe(
        (data) => {
          console.log("Data from products service", data);
          this.products = data; 
        },
        (error) => {
          console.log("Something went wrong", error);
        
        }
      );
  }
}