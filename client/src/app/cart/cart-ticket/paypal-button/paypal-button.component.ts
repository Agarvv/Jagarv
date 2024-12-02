import { Component } from '@angular/core';
import { PaypalService } from '@services/payments/paypal/paypal-service.service';

@Component({
  selector: 'app-paypal-button',
  templateUrl: './paypal-button.component.html',
  styleUrl: './paypal-button.component.css'
})
export class PaypalButtonComponent {
  constructor(private paypalService: PaypalService) {}
  
  payWithPayPal(): void {
     this.paypalService.payWithPaypal()
     .subscribe((data: any) => {
       console.log(data.data); // paypal url 
       window.location.href = data.data
     }, (error) => {
       console.error(error);
     })
  }
}
