import { Component, OnInit } from '@angular/core';

import { UserService } from '@services/user/user.service'; 
import { Store } from '@ngrx/store';
import { setError, clearMessages } from '@store/admin/admin.actions'
import { User } from '@models/User/User'; 

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit {
   constructor(private userService: UserService, private store: Store) {}
   
   ngOnInit(): void {
       this.store.dispatch(clearMessages()); 
       this.userService.getUserData()
       .subscribe((data: User) => {
           console.log('User data', data)
       }, (error: any) => {
           console.error('Error user data', error);
           this.store.dispatch(setError({
               errorMessage: 'Something Went Wrong While Fetching Your Data...'
           }))
       })
   }
}
