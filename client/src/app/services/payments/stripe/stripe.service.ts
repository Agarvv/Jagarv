import { Injectable } from '@angular/core';
import { loadStripe, Stripe, StripeElements } from '@stripe/stripe-js';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class StripeService {
  private stripe: Stripe | null = null;
  private elements: StripeElements | null = null;

  constructor() {
    loadStripe(environment.stripePublicKey).then((stripe: Stripe | null) => {
      if (stripe) { 
        this.stripe = stripe;
        this.elements = stripe.elements();
      }
    });
  }

  async createPaymentMethod(cardElement: any): Promise<any> {
    if (!this.stripe) {
      throw new Error('Stripe is not initzialized');
    }
    return this.stripe.createPaymentMethod({
      type: 'card',
      card: cardElement,
    });
  }

  createCardElement() {
    if (!this.elements) {
      throw new Error('Stripe Elements not initialized');
    }
    return this.elements.create('card');
  }
}
