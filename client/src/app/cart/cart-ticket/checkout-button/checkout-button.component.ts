import { Component } from '@angular/core';
import { environment } from '@env/environment';
import { StripeService } from '@services/payments/stripe/stripe.service';
import { loadStripe } from '@stripe/stripe-js';
import { Store } from '@ngrx/store'
import { setLoading, clearMessages } from '@store/admin/admin.actions'
import { finalize } from 'rxjs';

@Component({
  selector: 'app-checkout-button',
  templateUrl: './checkout-button.component.html',
  styleUrl: './checkout-button.component.css'
})
export class CheckoutButtonComponent {
  constructor(private stripeService: StripeService, private store: Store) {}

  pay() {
    this.store.dispatch(clearMessages());
    this.store.dispatch(setLoading({ isLoading: true }))
    this.stripeService.createCheckoutSession().pipe(
     finalize(() => {
      this.store.dispatch(setLoading({ isLoading: false}))
     })
    )
    .subscribe(async data => {
      console.log(data);
      const stripe = await loadStripe(environment.stripePublicKey);
      if(stripe) {
        window.location.href = data.data; 
      }else {
        console.error('Failed to load Stripe SDK'); 
      }
    }, (error) => {
      console.error('Error creating checkout session:', error);
    });
  }
}
