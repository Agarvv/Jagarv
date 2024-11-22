import { Component, Input } from '@angular/core';
import { MediaServiceService } from '@services/media/media-service.service';
import { UserService } from '@services/user/user.service';
import { User } from '@models/User/User'; 
import { Store } from '@ngrx/store';
import { setError, setLoading, clearMessages } from '@store/admin/admin.actions';
import { finalize } from 'rxjs';

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
    private userService: UserService,
    private store: Store
  ) {}

  onFileSelected(event: Event): void {
    this.store.dispatch(clearMessages()); 
    
    this.store.dispatch(setLoading({
      isLoading: true 
    }));
      
    const input = event.target as HTMLInputElement;
    
    if (input.files?.length) {
      this.selectedFile = input.files[0];
      
      this.mediaService.uploadProductImage(this.selectedFile).subscribe(
        (data: any) => {
          console.log('cloudinary', data);
          
          this.userService.setUserPicture(data.secure_url).pipe(
            finalize(() => {
              this.store.dispatch(setLoading({
                isLoading: false
              }));
            })
          ).subscribe(
            (data: any) => {
              console.log('Setted profile pic', data);
              window.location.reload();
            },
            (error) => {
              this.store.dispatch(setError({
                errorMessage: "Something Went Wrong..."
              }));
            }
          );
        },
        (error) => {
          console.error(error);
          this.store.dispatch(setLoading({
            isLoading: false
          }));
          this.store.dispatch(setError({
            errorMessage: "Error while uploading Your picture..."
          }));
        }
      );
    }
  }
}