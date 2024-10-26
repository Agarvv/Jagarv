import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrl: './edit-product.component.css'
})
export class EditProductComponent {
  constructor(private route: ActivatedRoute) {}
  
  ngOnInit(): void {
  this.route.params.subscribe(params => {
    const productId = params['productId']; 
  });
}
}
