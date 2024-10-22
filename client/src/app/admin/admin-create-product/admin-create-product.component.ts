import { Component, OnInit } from '@angular/core'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { CreateProductServiceStateService } from '../../state/product/create-product-service-state.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'] 
})
export class AdminCreateProductComponent implements OnInit {

  isLoading = false;   
  successMessage: string | null = null;   
  errorMessage: string | null = null;     

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
    this.productForm.get('pictures')?.setValue(this.createProductStateService.getImages());
  }

  createProduct(): void {
    if (this.productForm.invalid) {
      return;
    }

    const productData = this.productForm.value;  
    this.successMessage = null;
    this.errorMessage = null;
    this.isLoading = true;  

    this.productsService.createProduct(productData).pipe(
      finalize(() => this.isLoading = false)  
    ).subscribe(
      (data) => {
        this.successMessage = "Product Created Successfully!";
        this.errorMessage = null; 
      },
      (error) => {
        this.errorMessage = "Something Went Wrong, Please Try Later...";
        // Debug
        console.error(error);
        this.successMessage = null;  
      }
    );
  }
}
