import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-view-product-button',
  templateUrl: './view-product-button.component.html',
  styleUrl: './view-product-button.component.css'
})
export class ViewProductButtonComponent {
   // this prop will be used to navigate to the '/product/productId' page with a routerLink in the html
  @Input() productId!: number; 
}
