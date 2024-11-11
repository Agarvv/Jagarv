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
  const productId = this.route.snapshot.paramMap.get('productId');
  if (productId) {  
    this.productsService.getProductById(Number(productId)).subscribe((data: Product) => {
      this.product = data;
      
      const attributesArray = this.variantForm.get('attributes') as FormArray;
      this.product?.category?.attributes.forEach(() => {
        attributesArray.push(this.fb.control(''));
      });
    }, (error) => {
      console.error("Error", error);
    });
  } else {
    console.error("Product not found");
  }
}
  
// when the pictures of our images component change,
// sets them to the form and checks if all is valid
onPicturesChange(pictures: string[]): void {
  this.variantForm.get('pictures')?.setValue(pictures);
  console.log("new pictures", this.variantForm.get('pictures')); // debug
  this.variantForm.get('pictures')?.updateValueAndValidity(); 
}


}