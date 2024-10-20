import { Component } from '@angular/core'; 
import { ProductsService } from '../../services/admin/products/products.service';
import { CreateProductServiceStateService } from '../../state/product/create-product-service-state.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'] // <- Aquí el cambio
})
export class AdminCreateProductComponent {
   // The new Product Object that will be sendt to the server 
   newProduct = {
       title: '',
       description: '',
       price: 0,
       category: '', 
       featured: false,
       pictures: [] as any[] // im not sure of what type the images can be, so im gonna let it in 'any'
   };

   constructor(
       private productsService: ProductsService,
       private createProductStateService: CreateProductServiceStateService 
   ) {
       this.newProduct.pictures = this.createProductStateService.getImages();
   }
   
   // This is the method that sends the request to the server
   createProduct(): void {
       this.productsService.createProduct(this.newProduct).subscribe((data) => {
           console.log("Server Created Product", this.newProduct);
       }, (error) => {
           console.error("Server not created product", error);
       });
   }
}
