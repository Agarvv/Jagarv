import { Component, Input } from '@angular/core';
import { ProductsService } from '@services/admin/products/products.service';
import { FormGroup } from '@angular/forms';
import { finalize } from 'rxjs';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '@store/admin/admin.actions';


@Component({
  selector: 'app-product-variant-submit-button',
  templateUrl: './product-variant-submit-button.component.html',
  styleUrl: './product-variant-submit-button.component.css'
})
export class ProductVariantSubmitButtonComponent {
  @Input() form: FormGroup | null = null;
  constructor(private productsService: ProductsService, private store: Store) {
     
  }
  
  
  addProductVariant(): void {
    if (this.form && this.form.valid) {
        console.log("form received", this.form);
        this.store.dispatch(setLoading({ isLoading: true }));
        
        this.productsService.addProductVariant(this.form.value).pipe(
            finalize(() => {
                this.store.dispatch(setLoading({ isLoading: false }));
                this.form?.reset();
            })
        ).subscribe({
            next: (data) => {
                this.store.dispatch(setSuccess({ successMessage: "Variant added successfully" }));
            },
            error: (error) => {
                this.store.dispatch(setError({ errorMessage: error.error }));
            }
        });
    } else {
        this.form?.markAllAsTouched();
    }
}

  
}
