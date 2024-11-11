import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { PublicProductsService } from '@services/products/products.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from '@models/Product';
import { NonEmptyArrayValidator } from '@validators/non-empty-array.validator';
import { CreateProductServiceStateService } from 'app/state/admin/product/create-product-service-state.service';

@Component({
  selector: 'app-add-product-variant',
  templateUrl: './add-product-variant.component.html',
  styleUrls: ['./add-product-variant.component.css']
})
// add product variant form 
export class AddProductVariantComponent implements OnInit {
  product: Product | undefined;
  variantForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private productsService: PublicProductsService,
    private route: ActivatedRoute,
    private productStateService: CreateProductServiceStateService 
  ) {
    this.variantForm = this.fb.group({
      price: ['', [Validators.required]], 
      stock: ['', [Validators.required]],
      attributes: this.fb.array([]),
      pictures: [[], [NonEmptyArrayValidator.nonEmptyArray]]
    });
  }

  ngOnInit(): void {
    this.getProduct(); 
  }

  getProduct(): void {
    // takes product id of the route param 
    const productId = this.route.snapshot.paramMap.get('productId');
    
    // if product id is in the route param, 
    if (productId) {  
      // calls the service to get the product.
      this.productsService.getProductById(Number(productId)).subscribe((data: Product) => {
        this.product = data;
        
        // inits attribute array form control 
        const attributesArray = this.variantForm.get('attributes') as FormArray;
        this.product?.category?.attributes.forEach(() => {
          attributesArray.push(this.fb.control(''));
        });

        // patches value of the product to the inputs
        this.variantForm.patchValue({
          price: this.product?.price || '', // Valor por defecto si price es undefined
          stock: this.product?.stock || ''  // Valor por defecto si stock es undefined
        });
      }, (error) => {
        // if something wrong, debug for developers.
        console.error("Error", error);
      });
    } else {
      // if product not exists, logs on the console.
      // probably i will add error message on the ui 
      console.error("Product not found");
    }
  }
  
  // when pictures of the component change,
  // checks their valability on the form.
  onPicturesChange(pictures: string[]): void {
    this.variantForm.get('pictures')?.setValue(pictures);
    console.log("new pictures", this.variantForm.get('pictures')); // debug
    this.variantForm.get('pictures')?.updateValueAndValidity(); 
  }
}