import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PublicProductsService } from "@services/products/products.service"
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions';


@Component({
  selector: 'app-products-by-category',
  templateUrl: './products-by-category.component.html',
  styleUrls: ['./products-by-category.component.css']
})
// handles product category read logic
// if user enters '/category/:category', it will find the products with that category in the server.
export class ProductsByCategoryComponent implements OnInit {
  category: string | ''; 

  constructor(private route: ActivatedRoute, private productsService: PublicProductsService, private store: Store) { }

  ngOnInit(): void {
      
    this.category = this.route.snapshot.paramMap.get('category')!;
    console.log(this.category); 
    
    this.getProductsByCategory();
    
  }
  
  getProductsByCategory(): void {
      
      this.store.dispatch(clearMessages());
      
      this.productsService.findProductsByCategory(this.category).subscribe((data: any) => {
          
        console.log(`Producys by category ${this.category}, ${data}`);
        
    }, (error: any) => {
        
        console.error(error); 
        this.store.dispatch(setError({ errorMessage: "Something went wrong, Please try again later.."}));
        
    })
    
  }
  
  
}