import { Component, Input } from '@angular/core';
import { BanService } from '@services/ban/ban.service'
import { Store } from '@ngrx/store'
import { clearMessages, setError } from '@store/admin/admin.state'

@Component({
  selector: 'app-ban-user',
  templateUrl: './ban-user.component.html',
  styleUrl: './ban-user.component.css'
})
export class BanUserComponent {
 @Input() userId: number | null = null; 
 
 constructor(private banService: BanService, private store: Store) {} 
 
 banOrUnban(): void {
     this.store.dispatch(clearMessages());
     this.banService.banOrUnbanUser(userId)
     .subscribe((data) => {
         console.log('ban data', data)
     }, (error) => {
         console.error(error);
         this.store.dispatch(setError({
             errorMessage: 'Something went wrong...'
         }))
     })
 }
 
}
