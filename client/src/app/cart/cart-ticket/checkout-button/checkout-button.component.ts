import { Component } from '@angular/core';
import { environment } from '@env/environment';
import { StripeService } from '@services/payments/stripe/stripe.service';
import { loadStripe } from '@stripe/stripe-js';

@Component({
  selector: 'app-checkout-button',
  templateUrl: './checkout-button.component.html',
  styleUrl: './checkout-button.component.css'
})
export class CheckoutButtonComponent {
  constructor(private stripeService: StripeService) {}

  pay() {
    this.stripeService.createCheckoutSession().subscribe(async data => {
      console.log(data);
      const stripe = await loadStripe(environment.stripePublicKey);
      if(stripe) {
        window.location.href = data.data; // url
      }else {
        console.error('Failed to load Stripe SDK'); // debug
      }
    }, (error) => {
      console.error('Error creating checkout session:', error);
    });
  }
}
