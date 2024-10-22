import { Component, OnInit } from '@angular/core'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { CreateProductServiceStateService } from '../../state/product/create-product-service-state.service';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'] 
})
export class AdminCreateProductComponent implements OnInit {

  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,  
    private productsService: ProductsService, 
    private createProductStateService: CreateProductServiceStateService  
  ) {
    
    this.productForm = this.fb.group({
      title: ['', Validators.required],  
      description: ['', Validators.required], 
      price: [0, [Validators.required, Validators.min(0)]],  
      category: ['', Validators.required],
      featured: [false],  
      pictures: [[]]  
    });
  }

  ngOnInit(): void {
    this.productForm.get('pictures')?.setValue(this.createProductStateService.getImages().map(img => img));
  }

  createProduct(): void {
    if (this.productForm.invalid) {
      return;
    }


    const productData = this.productForm.value;  
    
    
    this.productsService.createProduct(productData).subscribe(
      (data) => {
        console.log("Producto creado en el servidor:", data);
      },
      (error) => {
        console.error("Error al crear el producto en el servidor:", error);
      }
    );
  }
}