import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AdminStateManagerService } from '../../services/admin-state-manager.service';
import { ProductsService } from '../../services/products.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-admin-create-product',
  templateUrl: './admin-create-product.component.html',
  styleUrls: ['./admin-create-product.component.css'],
  providers: [AdminStateManagerService] // Local provider 
})
export class AdminCreateProductComponent implements OnInit {
  @Output() statusUpdate = new EventEmitter<{ isLoading: boolean, errorMessage: string | null, successMessage: string | null }>();

  productForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productsService: ProductsService,
    private adminStateManager: AdminStateManagerService 
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

    this.adminStateManager.setLoading(true);
    this.adminStateManager.clearMessages();

    this.productsService.createProduct(productData).pipe(
      finalize(() => {
        this.adminStateManager.setLoading(false);
      })
    ).subscribe(
      (data) => {
        this.adminStateManager.setSuccess('Product created successfully!');
      },
      (error) => {
        this.adminStateManager.setError('Something went wrong, please try again later.');
        console.error(error);
      }
    );
  }
}