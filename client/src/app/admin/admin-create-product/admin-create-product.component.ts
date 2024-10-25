import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs/operators';  3
import { PriceValidator } from '../../validators/price.validator';
import { NonEmptyArrayValidator } from '../../validators/non-empty-array.validator';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'],
})
export class AdminCreateProductComponent implements OnInit {
  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productsService: ProductsService,
    private store: Store
  ) {
    this.productForm = this.fb.group({
      title: ['', Validators.required],  // required
      description: ['', Validators.required],  // required
      price: [0, [Validators.required, PriceValidator.isValidPrice]],  // price should be greater than 0
      category: ['', Validators.required],  // required
      featured: [false],
      pictures: [[], [NonEmptyArrayValidator.nonEmptyArray]]  // the pictures array should have at least one element
    });
  }

  ngOnInit(): void {}

  createProduct(): void {
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched();  // Mark all fields as touched to show error messages
      return;
    }

    const productData = this.productForm.value;

    // Dispatch loading state
    this.store.dispatch(setLoading({ isLoading: true }));
    this.store.dispatch(clearMessages());

    this.productsService.createProduct(productData).pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));  // Stops the charging state when all is finished
        this.productForm.reset() // Resets the form, avoiding server-abuses.
      })
    ).subscribe(
      (data) => {
        // Dispatch success message
        this.store.dispatch(setSuccess({ successMessage: 'Product Created Succesfully' }));
      },
      (error) => {
        // Dispatch error message
        this.store.dispatch(setError({ errorMessage: error.error}));
        console.error(error); // Our backend handles errors already,
        // if the error status is 500 our backend will show:
        // 'Something went wrong..'
        // if the product already exists, 
        // will show something like 'The product already exists, try with another title.'
      }
    );
  }

    // When the product images of the child component change, this function will verify
    // if the images array is still valid
  
    onPicturesChange(pictures: string[]): void {
      this.productForm.get('pictures')?.setValue(pictures);
      this.productForm.get('pictures')?.updateValueAndValidity(); 
    }
}
