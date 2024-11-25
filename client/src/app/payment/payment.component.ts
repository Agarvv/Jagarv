 import { Component, OnInit } from '@angular/core';
import { StripeService } from "@services/payments/stripe/stripe.service"

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit {
  /* cardElement: any;
  cardErrors: string = '';

  constructor(private stripeService: StripeService) {} 
  
  async ngOnInit(): Promise<void> {
    try {
      await this.stripeService.ensureInitialized();
      this.cardElement = await this.stripeService.createCardElement();
  
      if (this.cardElement && typeof this.cardElement.mount === 'function') {
        this.cardElement.mount('#card-element');
      } else {
        throw new Error('Could not create stripe card');
      }
    } catch (error) {
      console.error(error);
      this.cardErrors = 'Something went wrong..'
    }
  }
  

  async handlePayment() {
    // Crear el PaymentMethod con el token
    const { paymentMethod, error } = await this.stripeService.createPaymentMethod(this.cardElement);

     
  }

  */
}
