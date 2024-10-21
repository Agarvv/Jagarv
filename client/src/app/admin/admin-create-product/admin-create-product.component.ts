import { Component, OnInit } from '@angular/core'; 
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { CreateProductServiceStateService } from '../../state/product/create-product-service-state.service';
import { Decimal } from 'decimal.js';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'] 
})
export class AdminCreateProductComponent implements OnInit {

  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,  
    private productsService: ProductsService, //that will handle the product creation
    
    private createProductStateService: CreateProductServiceStateService  // Inject the state service for images
  ) {
    // Initialize the product form 
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
    // Set the value of the 'pictures' control from the state service when the component initializes
    this.productForm.get('pictures')?.setValue(this.createProductStateService.getImages());
  }

  createProduct(): void {
    
    if (this.productForm.invalid) {
      return;  // just exits if the form is'nt valid
    }

    const formData = new FormData();  
    Object.keys(this.productForm.value).forEach(key => {
      if (key === 'pictures') {
        this.productForm.value.pictures.forEach((image: any) => {
          formData.append('pictures', image.file); 
        });
      } else {
        formData.append(key, this.productForm.value[key]);
      }
    });

    // Call the product service to create the product on the server
    this.productsService.createProduct(formData).subscribe((data) => {
      console.log("Server Created Product", this.productForm.value);
    }, (error) => {
     
      console.error("Server not created product", error);
    });
  }
}