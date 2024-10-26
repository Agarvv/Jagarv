ñimport { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs/operators';  
import { PriceValidator } from '../../validators/price.validator';
import { NonEmptyArrayValidator } from '../../validators/non-empty-array.validator';
import { Product } from '../../models/product.model'; 

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
    this.productsService.getProductById(id).subscribe(product => {
      this.productForm.patchValue(product);
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
        this.productForm.reset();
      })
    ).subscribe(
      (data) => {
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