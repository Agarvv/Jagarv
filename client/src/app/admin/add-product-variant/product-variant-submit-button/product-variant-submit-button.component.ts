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
// Submit button to add product variant form
export class ProductVariantSubmitButtonComponent {
  @Input() form: FormGroup | null = null;

  constructor(private productsService: ProductsService, private store: Store) {}

  addProductVariant(): void {
    if (this.form && this.form.valid) {
        
        this.clearAttributesArray();
        
        // Debug
        console.log("form received", this.form.value);
        
        // Loading state
        this.store.dispatch(setLoading({ isLoading: true }));
        
        // Invokes service function to call the server
        this.productsService.addProductVariant(this.form.value).pipe(
            finalize(() => {
                // Stops loading when finished
                this.store.dispatch(setLoading({ isLoading: false }));
                this.form?.reset();
            })
        ).subscribe({
            next: (data) => {
                // Success message if all ok
                this.store.dispatch(setSuccess({ successMessage: "Variant added successfully" }));
            },
            error: (error) => {
                // Error message if issue
                this.store.dispatch(setError({ errorMessage: error.error }));
            }
        });
    } else {
        // Form touched if not valid
        this.form?.markAllAsTouched();
    }
  }

  clearAttributesArray(): void {
    // cleans the empty strings of the array, converts to numbers and removes zeros
    this.form.get('attributes')?.setValue(
      this.form.get('attributes')?.value
        .map((element: string) => Number(element)) 
        .filter((element: number) => element !== 0) 
    );
    
    console.log("Attributes cleaned", this.form.get('attributes')?.value);
  }
}