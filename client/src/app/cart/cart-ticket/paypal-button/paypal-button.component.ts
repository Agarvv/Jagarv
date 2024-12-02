import { Component } from '@angular/core';
import { PaypalService } from '@services/payments/paypal/paypal-service.service';
import { Store } from '@ngrx/store'
import { setLoading, clearMessages } from '@store/admin/admin.actions'
import { finalize } from 'rxjs';

@Component({
  selector: 'app-paypal-button',
  templateUrl: './paypal-button.component.html',
  styleUrl: './paypal-button.component.css'
})
export class PaypalButtonComponent {
  constructor(private paypalService: PaypalService, private store: Store) {}
  
  payWithPayPal(): void {
    this.store.dispatch(clearMessages())
    this.store.dispatch(setLoading({ isLoading: true }))
     this.paypalService.payWithPaypal()
     .pipe(
       finalize(() => this.store.dispatch(setLoading({ isLoading: false })))
     )
     .subscribe((data: any) => {
       console.log(data.data); // paypal url 
       window.location.href = data.data
     }, (error) => {
       console.error(error);
     })
  }
}
