import { Component } from '@angular/core';
import { PaypalService } from '@services/payments/paypal/paypal-service.service';

@Component({
  selector: 'app-paypal-button',
  templateUrl: './paypal-button.component.html',
  styleUrl: './paypal-button.component.css'
})
export class PaypalButtonComponent {
  constructor() {}
  
  payWithPayPal(): void {
    
  }
}
