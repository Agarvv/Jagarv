import { Component, OnInit, Input } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// The 'ProductsService' handles all CRUD admin logic.
import { ProductsService } from '../../services/admin/products/products.service';
import { Store } from '@ngrx/store';
import { setLoading, setSuccess, setError, clearMessages } from '../../store/admin/admin.actions';
import { finalize } from 'rxjs/operators';  
import { PriceValidator } from '../../validators/price.validator';
import { NonEmptyArrayValidator } from '../../validators/non-empty-array.validator';
import { Product } from '../../models/Product'; 
// And the 'PublicProductsService' handles all the accesible products in the app logic,
// like display products by category, and other 'non-admin' things.
import { PublicProductsService } from '../../services/products/products.service';
import { CreateProductServiceStateService } from '../../state/admin/product/create-product-service-state.service';

// This component is used to create or edit a product, two in one.
@Component({
  selector: 'app-admin-product-form',
  templateUrl: './admin-product-form.component.html',
  styleUrls: ['./admin-product-form.component.css'],
})
export class AdminProductFormComponent implements OnInit {
  // if this prop 'productId' is passed to the component, that means we have to
  // edit the product with that id.
  @Input() productId?: number; 

  productForm: FormGroup; // this is just the product form
  isEditing: boolean = false; // This is set to true on a ngOnInit() if the 'productId' prop is passed.

  // we have many dependencies and services here
  constructor(
    private fb: FormBuilder,
    private productsService: ProductsService,
    private publicProductsService: PublicProductsService,
    private productStateService: CreateProductServiceStateService,
    private store: Store
  ) {
    this.productForm = this.fb.group({
      title: ['', Validators.required], // title is required
      description: ['', Validators.required], // description is required
      price: [0, [Validators.required, PriceValidator.isValidPrice]], // price is required and should be bigger than 0.
      category: ['', Validators.required], // the category is required
      stock: [0, [Validators.required, Validators.min(0)]], // stock should be a non-negative number
      featured: [false], // the featured is not required
      pictures: [[], [NonEmptyArrayValidator.nonEmptyArray]] // the pictures [] should have at least one url.
    });
  }

  ngOnInit(): void {
    if (this.productId) {
      this.isEditing = true; // if the 'productId' prop is passed, isEditing turns to true
      this.loadProductData(this.productId); // and gets the product to edit by his ID
    }
  }
  
  // calls our public (not for admins, public.) product service to find a product by his id
  loadProductData(id: number): void {
    this.publicProductsService.getProductById(id).subscribe(product => {
      this.productForm.patchValue(product);
      if (product.pictures) {
        // if the product has pictures, adds the image to our service state.
        // and our product-form-images component takes them, and shows them.
        this.productStateService.resetImages(); 
        product.pictures.forEach((image: string) => this.productStateService.addImage(image));  
      }
    });
  }

  submitProduct(): void {
    // checks if the form is valid. If not, marks all fields as touched and returns
    if (this.productForm.invalid) {
      this.productForm.markAllAsTouched();
      return;
    }

    const productData = this.productForm.value;

    this.store.dispatch(setLoading({ isLoading: true }));
    this.store.dispatch(clearMessages());
    
    // if we are editing a product, the updateProduct() function is called,
    // if not, that means we are creating a product, then calls the createProduct() method.
    const productRequest = this.isEditing 
      ? this.productsService.updateProduct(this.productId!, productData)  
      : this.productsService.createProduct(productData);

    productRequest.pipe(
      finalize(() => {
        this.store.dispatch(setLoading({ isLoading: false }));
        if (!this.isEditing) {
          this.productForm.reset();
          this.productStateService.resetImages();
        }
      })
    ).subscribe(
      (data) => {
        console.log('Product updated!', data)
        this.store.dispatch(setSuccess({ successMessage: this.isEditing ? 'Product Updated Successfully' : 'Product Created Successfully' }));
      },
      (error) => {
        this.store.dispatch(setError({ errorMessage: error.error }));
        console.error(error);
      }
    );
  }
  
  // this method is called when the product-form-images's pictures changes.
  onPicturesChange(pictures: string[]): void {
    this.productForm.get('pictures')?.setValue(pictures);
    this.productForm.get('pictures')?.updateValueAndValidity(); 
  }
}