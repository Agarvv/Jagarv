import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-edit-product-button',
  templateUrl: './edit-product-button.component.html',
  styleUrl: './edit-product-button.component.css'
})
// this component, on his HTML has a button to edit a product,
// if that button is clicked, the admin will be redirected to the
// "admin/editProduct/${productId}" page.
export class EditProductButtonComponent {
  @Input() productId!: number; 

}
