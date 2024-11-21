import { Component } from '@angular/core';
import { UsersService } from '../../services/admin/users/users.service';
import { AdminUser } from '@models/User/AdminUser'
import { Store } from '@ngrx/store'
import { setError, clearMessages } from '@store/admin/admin.actions'
 
 
@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrl: './admin-users.component.css'
})
export class AdminUsersComponent {
   // The users array will be filled with data from the server
   users: AdminUser[] = [];


   constructor(private usersService: UsersService, private store: Store) { }
   
   // gets the users from the server
   ngOnInit(): void {
     this.loadUsers();
   }
   
   loadUsers(): void {
      this.store.dispatch(clearMessages()); 
      
     this.usersService.getUsers().subscribe((data) => {
       console.log('Our server returned', data)
       this.users = data;
     }, (error) => {
       console.error('An error occurred', error)
       this.store.dispatch(setError({
           errorMessage: "Something Went Wrong..."
       }))
     })
   }
}
