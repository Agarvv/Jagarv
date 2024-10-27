import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.css']
})
// this component uses our 'admin-product-form' to edit a product,
// taking the productId of the route params, and sending it to our 'admin-product-form' as a prop.
// check the component's HTML for better understanding.
export class EditProductComponent implements OnInit {
  productId!: number;  

  constructor(private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.productId = +params['productId']; 
    });
  }
}
