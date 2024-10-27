import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// The 'ProductsService' handles all CRUD admin logic.
import { ProductsService } from '../../services/admin/products/products.service';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs/operators';  
import { PriceValidator } from '../../validators/price.validator';
import { NonEmptyArrayValidator } from '../../validators/non-empty-array.validator';
import { Product } from '../../models/Product'; 
// And the 'PublicProductsService' handles all the accesible products in the app logic,
// like display products by category, and other 'non-admin' things.
import { PublicProductsService } from '../../services/products/products.service'; 

@Component({
  selector: 'app-admin-product-form',
  templateUrl: './admin-product-form.component.html',
  styleUrls: ['./admin-product-form.component.css'],
})
export class AdminProductFormComponent implements OnInit {
  @Input() productId?: number;
  productForm: FormGroup;
  isEditing: boolean = false; 

  constructor(
    private fb: FormBuilder,
    private productsService: ProductsService,
    private publicProductsService: PublicProductsService,
    private store: Store
  ) {
    this.productForm = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, PriceValidator.isValidPrice]],
      category: ['', Validators.required],
      featured: [false],
      pictures: [[], [NonEmptyArrayValidator.nonEmptyArray]]
    });
  }

  ngOnInit(): void {
    if (this.productId) {
      this.isEditing = true;
      this.loadProductData(this.productId);
    }
  }

  loadProductData(id: number): void {
  this.publicProductsService.getProductById(id).subscribe(product => {
    this.productForm.patchValue(product);
    if (product.pictures) {
      this.productStateService.resetImages(); 
      product.pictures.forEach((image: string) => this.productStateService.addImage(image));  
    }
  });
}

  submitProduct(): void {
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched();
      return;
    }

    const productData = this.productForm.value;

    this.store.dispatch(setLoading({ isLoading: true }));
    this.store.dispatch(clearMessages());

    const productRequest = this.isEditing 
      ? this.productsService.updateProduct(this.productId!, productData) 
      : this.productsService.createProduct(productData);

    productRequest.pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));
        if (!this.isEditing) {
          this.productForm.reset();
          this.productStateService.resetImages();
        }
      })
    ).subscribe(
      (data) => {
        console.log('Product updated!', data)
        this.store.dispatch(setSuccess({ successMessage: this.isEditing ? 'Product Updated Successfully' : 'Product Created Successfully' }));
      },
      (error) => {
        this.store.dispatch(setError({ errorMessage: error.error }));
        console.error(error);
      }
    );
  }
  
  
  onPicturesChange(pictures: string[]): void {
    this.productForm.get('pictures')?.setValue(pictures);
    this.productForm.get('pictures')?.updateValueAndValidity(); 
  }
}