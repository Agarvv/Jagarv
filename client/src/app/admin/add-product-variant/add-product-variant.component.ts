import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ProductsService } from '@services/products/products.service';
import { ActivatedRoute } from '@angular/router';
import { Product } from '@models/Product';

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
    private productsService: ProductsService,
    private route: ActivatedRoute
  ) {
    this.variantForm = this.fb.group({
      price: ['', [Validators.required]],
      stock: ['', [Validators.required]],
      attributes: this.fb.array([])  
    });
  }

  ngOnInit(): void {
    this.getProduct();
  }

  getProduct(): void {
    const productId = this.route.snapshot.paramMap.get('productId');
    this.productsService.getProductById(productId).subscribe((data: Product) => {
      this.product = data;
      this.initializeForm();
    }, (error) => {
      console.error("Oops, something went wrong...", error);
    });
  }

}