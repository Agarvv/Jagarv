import { Component, OnInit } from '@angular/core';

import { UserService } from '@services/user/user.service'; 
import { Store } from '@ngrx/store';
import { setError, setLoading, clearMessages } from '@store/admin/admin.actions'
import { User } from '@models/User/User'; 
import { finalize } from 'rxjs'

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
    user: User | null = null; 
    
   constructor(private userService: UserService, private store: Store) {}
   
   ngOnInit(): void {
       
       this.store.dispatch(clearMessages()); 
       
       this.store.dispatch(setLoading({
           isLoading: true 
       }))
       this.userService.getUserData().pipe(
         finalize(() => {
             this.store.dispatch(setLoading({
                 isLoading: false
             }))
         })
        )
       
       .subscribe((data: User) => {
           
           console.log('User data', data)
           this.user = data; 
           
       }, (error: any) => {
           
           console.error('Error user data', error);
           this.store.dispatch(setError({
               errorMessage: 'Something Went Wrong While Fetching Your Data...'
               
           }))
       })
       
   }
}
