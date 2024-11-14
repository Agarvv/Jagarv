import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { OpinionsService } from '@services/products/opinions/opinions.service';
import { finalize } from 'rxjs';
import { Store } from '@ngrx/store';
import { setLoading, setError, clearMessages } from '@store/admin/admin.actions';

@Component({
  selector: 'app-create-opinion-submit',
  templateUrl: './create-opinion-submit.component.html',
  styleUrls: ['./create-opinion-submit.component.css']
})
export class CreateOpinionSubmitComponent {
   @Input() form!: FormGroup; 
   
   constructor(
     private opinionsService: OpinionsService,  
     private store: Store
   ) {}

  createOpinion(): void {
      this.store.dispatch(clearMessages());

      if (this.form?.invalid) { 
          this.form?.markAllAsTouched();
          return;
      }

      this.store.dispatch(setLoading({ isLoading: true }));

      this.opinionsService.createOpinion(this.form.value).pipe(
        finalize(() => {
            this.store.dispatch(setLoading({ isLoading: false }));
        })
      ).subscribe(
        (data: any) => {  
            console.log('Opinion created', data);
            window.location.reload();  // jusf for now
        }, 
        (error: any) => {  
            this.store.dispatch(setError({ errorMessage: error.error }));
        }
      );
  }
}