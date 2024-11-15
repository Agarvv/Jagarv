import { Component } from '@angular/core';
import { StripeService } from "@services/payments/stripe/stripe.service"

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent {
  constructor(private stripeService: StripeService) {} 
  
}
