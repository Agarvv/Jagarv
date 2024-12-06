import { Component, Input, OnInit } from '@angular/core';
import { UserService } from '@services/user/user.service'; 
import { Store } from '@ngrx/store'; 
import { clearMessages, setLoading, setError } from '@store/admin/admin.actions';
import { finalize } from 'rxjs'

@Component({
  selector: 'app-profile-adress',
  templateUrl: './profile-adress.component.html',
  styleUrls: ['./profile-adress.component.css']
})
export class ProfileAdressComponent implements OnInit {
  @Input() address: string | "" = "Your adress";  
  isEditable: boolean = false;  
  
  constructor
  (
   private store: Store,
   private userService: UserService
  ) 
  {
      
  } 

  toggleEdit() {
    this.isEditable = true;
  }

  saveAddress() {
    this.store.dispatch(clearMessages()); 
    
    this.store.dispatch(setLoading({ isLoading: true }))
    
    this.userService.setUserAdress(this.address)
    .pipe(
      finalize(() => {
          this.store.dispatch(setLoading({
              isLoading: false 
          }))
      })
    ).subscribe((data) => {
        window.location.reload(); 
    }, (error) => {
        this.store.dispatch(setError({
            errorMessage: 'Something Went Wrong..'
        }))
    })
  }

  updateAddress(event: any) {
    this.address = event.target.innerText;
  }

  ngOnInit(): void {
      console.log('adress prop', this.address)
  }
}