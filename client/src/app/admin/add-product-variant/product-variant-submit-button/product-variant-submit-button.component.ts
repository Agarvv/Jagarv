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
// submit button to add product variant form
export class ProductVariantSubmitButtonComponent {
  @Input() form: FormGroup | null = null;
  constructor(private productsService: ProductsService, private store: Store) {
     
  }
  
  addProductVariant(): void {
    if (this.form && this.form.valid) {
        // converts String number array to Number array. 
        this.form.get('attributes')?.value.map((value: string) => Number(value));
        
        // debug 
        console.log("form received", this.form.value);
        
        // loading state 
        this.store.dispatch(setLoading({ isLoading: true }));
        
        // invokes service function to call the server 
        this.productsService.addProductVariant(this.form.value).pipe(
            finalize(() => {
                // stops loading when finished 
                this.store.dispatch(setLoading({ isLoading: false }));
                this.form?.reset();
            })
        ).subscribe({
            next: (data) => {
                // success message if all ok
                this.store.dispatch(setSuccess({ successMessage: "Variant added successfully" }));
            },
            error: (error) => {
                // error message if issue
                this.store.dispatch(setError({ errorMessage: error.error }));
            }
        });
    } else {
        // form touched if not valid
        this.form?.markAllAsTouched();
    }
}

  
}
