import { Component } from '@angular/core';
import { UsersService } from '../../services/admin/users/users.service';
 
@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrl: './admin-users.component.css'
})
export class AdminUsersComponent {
   // The users array will be filled with data from the server
   users: any[] = [];
   error: String = "";

   constructor(private usersService: UsersService) { }
   
   // gets the users from the server
   ngOnInit(): void {
     this.loadUsers();
   }
   
   loadUsers(): void {
     this.usersService.getUsers().subscribe((data) => {
       console.log('Our server returned', data)
       this.users = data;
     }, (error) => {
       console.error('An error occurred', error)
       this.error = "Something Went Wrong, Please Try Again...";
     })
   }
}
