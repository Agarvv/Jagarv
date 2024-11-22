import { Component, Input } from '@angular/core';
import { MediaServiceService } from '@services/media/media-service.service';
import { UserService } from '@services/user/user.service';
import { User } from '@models/User/User'; 

@Component({
  selector: 'app-profile-data',
  templateUrl: './profile-data.component.html',
  styleUrls: ['./profile-data.component.css']
})
export class ProfileDataComponent {
  selectedFile: File | null = null;
  @Input() user: User | null = null; 
  
  constructor(
    private mediaService: MediaServiceService, 
    private userService: UserService
  ) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    
    if (input.files?.length) {
      this.selectedFile = input.files[0];
      
      this.mediaService.uploadProductImage(this.selectedFile).subscribe(
        (data: any) => {
          console.log('cloudinary', data);
          
          this.userService.setUserPicture(data.secure_url).subscribe(
            (data: any) => {
              console.log('Setted profile pic', data);
            },
            (error) => {
              console.error(error);
            }
          );
        },
        (error) => {
          console.error(error);
        }
      );
    }
  }
}