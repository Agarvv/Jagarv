import { Component, Input } from '@angular/core';
import { PaypalService } from '@services/payments/paypal/paypal-service.service';
import { Store } from '@ngrx/store'
import { setLoading, clearMessages } from '@store/admin/admin.actions'
import { finalize } from 'rxjs';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-paypal-button',
  templateUrl: './paypal-button.component.html',
  styleUrl: './paypal-button.component.css'
})
export class PaypalButtonComponent {
  @Input() reductionForm: FormGroup | null = null
  constructor(private paypalService: PaypalService, private store: Store) {}
  
  payWithPayPal(): void {
    console.log('reduction form', this.reductionForm?.value)
    this.store.dispatch(clearMessages())
    this.store.dispatch(setLoading({ isLoading: true }))
     this.paypalService.payWithPaypal(this.reductionForm?.value)
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
