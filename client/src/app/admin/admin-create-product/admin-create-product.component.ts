import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductsService } from '../../services/admin/products/products.service';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs/operators';  // Importar finalize

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
      title: ['', Validators.required],
      description: ['', Validators.required],
      price: [0, [Validators.required, Validators.min(0)]],
      category: ['', Validators.required],
      featured: [false],
      pictures: [[]]
    });
  }

  ngOnInit(): void {}

  createProduct(): void {
    if (this.productForm.invalid) {
      return;
    }

    const productData = this.productForm.value;

    // Dispatch loading state
    this.store.dispatch(setLoading({ isLoading: true }));
    this.store.dispatch(clearMessages());

    this.productsService.createProduct(productData).pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));  // Stops the charging state when all is finished
      })
    ).subscribe(
      (data) => {
        // Dispatch success message
        this.store.dispatch(setSuccess({ successMessage: 'Product Created Sucesfully' }));
      },
      (error) => {
        // Dispatch error message
        this.store.dispatch(setError({ errorMessage: 'Ooops, Something Went Wrong, Please Try Again later... :/' }));
        console.error(error);
      }
    );
  }
}
