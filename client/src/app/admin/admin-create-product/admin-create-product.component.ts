import { Component } from '@angular/core'; 
import { ProductsService } from '../../services/admin/products/products.service';
import { CreateProductServiceStateService } from '../../state/product/create-product-service-state.service';
import { Decimal } from 'decimal.js';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'] // <- Aquí el cambio
})
export class AdminCreateProductComponent {
   // The new Product Object that will be sendt to the server 
   newProduct = {
       title: '' as String,
       description: '' as String,
       price: new Decimal(0),
       category: '' as String, 
       featured: false as Boolean,
       pictures: [] as any[] 
   };

   constructor(
       private productsService: ProductsService,
       private createProductStateService: CreateProductServiceStateService 
   ) {
       this.newProduct.pictures = this.createProductStateService.getImages();
   }
   
  // We must use formData to handle images
  createProduct(): void {
  const formData = new FormData();

 
  formData.append('title', this.newProduct.title);
  
  formData.append('description', this.newProduct.description);
  
  formData.append('price', this.newProduct.price.toString());
  
  formData.append('category', this.newProduct.category);
  
  formData.append('featured', String(this.newProduct.featured));

  
  this.newProduct.pictures.forEach((image) => {
    formData.append('pictures', image.file); 
  });

 
  this.productsService.createProduct(formData).subscribe((data) => {
    console.log("Server Created Product", this.newProduct);
  }, (error) => {
    console.error("Server not created product", error);
  });
} 


}
