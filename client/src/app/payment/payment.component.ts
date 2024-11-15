import { Component, OnInit } from '@angular/core';
import { StripeService } from "@services/payments/stripe/stripe.service"

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {
  cardElement: any;
  cardErrors: string = '';

  constructor(private stripeService: StripeService) {} 
  
  ngOnInit(): void {
    this.cardElement = this.stripeService.createCardElement();
    this.cardElement.mount('#card-element')
  }

  async handlePayment() {
    // Crear el PaymentMethod con el token
    const { paymentMethod, error } = await this.stripeService.createPaymentMethod(this.cardElement);

     
  }

  
}
