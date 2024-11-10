import { Component, Input } from '@angular/core';
import { ProductsService } from '@services/admin/products/products.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-product-variant-submit-button',
  templateUrl: './product-variant-submit-button.component.html',
  styleUrl: './product-variant-submit-button.component.css'
})
export class ProductVariantSubmitButtonComponent {
  @Input() form: FormGroup | null = null;
  constructor(private productsService: ProductsService) {
     
  }

  addProductVariant(): void {
    if (this.form && this.form.valid) {
      this.productsService.(this.form.value).subscribe((variant) => {
        console.log('Product variant added successfully', variant);
        this.form.reset();
      });
    } else {
      this.form?.markAllAsTouched();
    }
  }
}
