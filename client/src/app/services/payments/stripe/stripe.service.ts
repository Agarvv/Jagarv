import { Injectable } from '@angular/core';
import { loadStripe, Stripe, StripeElements } from '@stripe/stripe-js';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class StripeService {
  private stripe: Stripe | null = null;
  private elements: StripeElements | null = null;

  async initStripe(): Promise<void> {
    if (!this.stripe) {
      const stripe = await loadStripe(environment.stripePublicKey);
      if (stripe) {
        this.stripe = stripe;
        this.elements = stripe.elements();
      } else {
        throw new Error('Stripe could not init'); // debug
      }
    }
  }

  async createPaymentMethod(cardElement: any): Promise<any> {
    if (!this.stripe) {
      throw new Error('Stripe not initzialized'); // debug
    }
    return this.stripe.createPaymentMethod({
      type: 'card',
      card: cardElement,
    });
  }

  createCardElement() {
    if (!this.elements) {
      throw new Error('Stripe Elements not initzialized'); // debug
    }
    return this.elements.create('card');
  }
}
