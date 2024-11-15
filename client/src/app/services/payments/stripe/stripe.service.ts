import { Injectable } from '@angular/core';
import { loadStripe } from '@stripe/stripe-js';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root',
})
export class StripeService {
  private stripe: any;
  private elements: any;

  constructor() {
    loadStripe(environment.stripePublicKey).then((stripe) => {
      this.stripe = stripe;
      this.elements = stripe.elements(); 
    });
  }

  createPaymentMethod(cardElement: any): Promise<any> {
    return this.stripe.createPaymentMethod({
      type: 'card',
      card: cardElement,
    });
  }

  createCardElement() {
    return this.elements.create('card'); 
  }
}