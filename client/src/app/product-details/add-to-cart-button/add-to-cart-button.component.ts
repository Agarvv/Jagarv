import { Component, Input } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { ProductDetailsState } from '@store/cart/product-details.state';
import { Product } from '@models/Product';
import { CartService } from "@services/cart/cart.service"
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-to-cart-button',
  templateUrl: './add-to-cart-button.component.html',
  styleUrls: ['./add-to-cart-button.component.css']
})
export class AddToCartButtonComponent {
  @Input() inCart: boolean | false = false; 
  addProductForm: FormGroup;
  product$: Observable<Product | null>;
  options$: Observable<{ attributeName: string; attributeOptionId: number }[]> | null;

  constructor(
    private fb: FormBuilder,
    private cartService: CartService,
    private store: Store<ProductDetailsState
    private router: Router
  ) {
    this.product$ = this.store.pipe(select((state: any) => state.productDetails.product));
    this.options$ = this.store.pipe(select((state: any) => state.productDetails.attributes));

    this.addProductForm = this.fb.group({
      productId: ['', Validators.required],
      options: this.fb.array([], this.inCart ? [] : Validators.required)  
    });

    this.options$.subscribe(options => {
      const optionIds = options?.map(option => option.attributeOptionId) || [];
      this.setOptions(optionIds);
    });

    this.product$.subscribe(product => {
      if (product) {
        this.addProductForm.patchValue({
          productId: product.id
        });
      }
    });
  }

  private setOptions(optionIds: number[]) {
    const optionsArray = this.addProductForm.get('options') as FormArray;
    optionsArray.clear(); 
    
    optionIds.forEach(id => {
      optionsArray.push(this.fb.control(id));
    });
  }
  
  // ad or remove product of cart
  addToCart(): void {
    console.log('value', this.addProductForm.value);
    // if product is in cart that means or user wants to delete it
    if (this.inCart || this.addProductForm.valid) {  
      this.cartService.addOrRemoveToCart(this.addProductForm.value).subscribe(
        (data) => {
          console.log("Added to cart");  // debug
          this.router.navigate(['/cart'])
        },
        (error) => {
          console.error(error);  // debug
        }
      );
    } else {
      console.error("Form not valid");
    }
  }
}