import { Component } from '@angular/core';

@Component({
  selector: 'app-profile-adress',
  templateUrl: './profile-adress.component.html',
  styleUrls: ['./profile-adress.component.css']
})
export class ProfileAdressComponent {
  address: string = 'Your Address';  
  isEditable: boolean = false;  


  toggleEdit() {
    this.isEditable = true;
  }

  saveAddress() {
    this.isEditable = false;
    alert('Adress saved: ' + this.address);
  }

  updateAddress(event: any) {
    this.address = event.target.innerText;
  }
}