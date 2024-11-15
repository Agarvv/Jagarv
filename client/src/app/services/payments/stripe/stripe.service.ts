import { Injectable } from '@angular/core';
import { loadStripe, Stripe, StripeElements } from '@stripe/stripe-js';
import { environment } from '@env/environment';

@Injectable({
  providedIn: 'root',
})
export class StripeService {
  private stripe: Stripe | null = null;
  private elements: StripeElements | null = null;
  private initialized: Promise<void>;

  constructor() {
    this.initialized = this.initializeStripe();
  }

  private async initializeStripe(): Promise<void> {
    const stripe = await loadStripe(environment.stripePublicKey);
    if (!stripe) {
      throw new Error('could not init stripe');
    }
    this.stripe = stripe;
    this.elements = stripe.elements();
  }

  async ensureInitialized(): Promise<void> {
    return this.initialized; 
  }

  async createPaymentMethod(cardElement: any): Promise<any> {
    await this.ensureInitialized();
    if (!this.stripe) {
      throw new Error('Stripe not init');
    }
    return this.stripe.createPaymentMethod({
      type: 'card',
      card: cardElement,
    });
  }

  async createCardElement() {
    await this.ensureInitialized();
    if (!this.elements) {
      throw new Error('Stripe Elements not init');
    }
    return this.elements.create('card');
  }
}
