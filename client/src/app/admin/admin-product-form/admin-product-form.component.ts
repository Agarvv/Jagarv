import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service'; // CRUD logic for products
import { Store } from '@ngrx/store'; // For state management
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions'; // Actions for state management
import { finalize } from 'rxjs/operators';  
import { PriceValidator } from '../../validators/price.validator'; // Custom validator for price
import { NonEmptyArrayValidator } from '../../validators/non-empty-array.validator'; // Custom validator for pictures array
import { Product } from '../../models/Product'; // Product model
import { PublicProductsService } from '../../services/products/products.service'; // Public product service for fetching product details
import { CreateProductServiceStateService } from '../../state/admin/product/create-product-service-state.service'; // Service for managing product images state

// This component is used for creating or editing a product
@Component({
  selector: 'app-admin-product-form',
  templateUrl: './admin-product-form.component.html',
  styleUrls: ['./admin-product-form.component.css'],
})
export class AdminProductFormComponent implements OnInit {
  // If this property 'productId' is passed, it means we need to edit the product with that ID
  @Input() productId?: number; 

  productForm: FormGroup; // Form for product data
  isEditing: boolean = false; // Indicates if we are editing an existing product

  // Dependencies and services are injected into the constructor
  constructor(
    private fb: FormBuilder, // FormBuilder to create the form
    private productsService: ProductsService, // Service for handling product CRUD operations
    private publicProductsService: PublicProductsService, // Public product service
    private productStateService: CreateProductServiceStateService, // Service for handling product images state
    private store: Store // Store for state management (ngrx)
  ) {
    // Form initialization with validators
    this.productForm = this.fb.group({
      title: ['', Validators.required], // Title is required
      description: ['', Validators.required], // Description is required
      price: [0, [Validators.required, PriceValidator.isValidPrice]], // Price is required and must be a valid price
      category: [null, Validators.required], // Category is required (ID will be number)
      stock: [0, [Validators.required, Validators.min(0)]], // Stock should be a non-negative number
      featured: [false], // Featured is not required
      pictures: [[], [NonEmptyArrayValidator.nonEmptyArray]] // Pictures array must contain at least one image URL
    });
  }

  ngOnInit(): void {
    // If a productId is passed, we need to edit that product
    if (this.productId) {
      this.isEditing = true; 
      this.loadProductData(this.productId); 
    }
  }

  // Method to load product data for editing
  loadProductData(id: number): void {
    this.publicProductsService.getProductById(id).subscribe(product => {
      this.productForm.patchValue(product);
      if (product.pictures) {
        // If the product has pictures, add them to the image state
        this.productStateService.resetImages();
        product.pictures.forEach((image: string) => this.productStateService.addImage(image));  
      }
    });
  }

  // Method to submit the product data (either create or update)
  submitProduct(): void {
    if (this.productForm.invalid) {
      console.log("Form invalid.."); // Debug log
      this.productForm.markAllAsTouched();
      return;
    }

    // Get the form data
    const productData = this.productForm.value;

    // Convert the category ID to a number if it's not already
    if (productData.category) {
      productData.category = Number(productData.category);
    }

    // Dispatch loading state and clear previous messages
    this.store.dispatch(setLoading({ isLoading: true }));
    this.store.dispatch(clearMessages());

    const productRequest = this.isEditing 
      ? this.productsService.updateProduct(this.productId!, productData)  // Update product if editing
      : this.productsService.createProduct(productData); // Create new product if not editing

    productRequest.pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false })); // Stop loading state
        if (!this.isEditing) {
          // If not editing, reset the form and image state
          this.productForm.reset();
          this.productStateService.resetImages();
        }
      })
    ).subscribe(
      (data) => {
        console.log('Product updated!', data); // Debug log for successful operation
        this.store.dispatch(setSuccess({
          successMessage: this.isEditing ? 'Product Updated Successfully' : 'Product Created Successfully'
        }));
      },
      (error) => {
        // Handle errors, dispatch error message
        this.store.dispatch(setError({ errorMessage: error.error }));
        console.error(error);
      }
    );
  }
  
  onPicturesChange(pictures: string[]): void {
    this.productForm.get('pictures')?.setValue(pictures); // Update the form control with the new pictures
    this.productForm.get('pictures')?.updateValueAndValidity();
  }
}