import { Component, ViewChild, ElementRef, Output, EventEmitter } from '@angular/core';
import { CreateProductServiceStateService } from '../../../state/admin/product/create-product-service-state.service';
import { MediaServiceService } from '../../../services/media/media-service.service';

@Component({
  selector: 'app-product-images-form',
  templateUrl: './product-images-form.component.html',
  styleUrls: ['./product-images-form.component.css']
})
export class ProductImagesFormComponent {
  images: string[] = [];  // Array to store image URLs
  @ViewChild('fileInput') fileInput!: ElementRef; 

  // Output event to notify the parent component about the image array changes
  @Output() picturesChange = new EventEmitter<string[]>();

  constructor(
    private productStateService: CreateProductServiceStateService,
    private mediaService: MediaServiceService
  ) {
    this.images = this.productStateService.getImages();  // Get the images from the state service
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];  
    if (file && file.type.startsWith('image/')) {
      this.mediaService.uploadProductImage(file).subscribe((data) => {
          const finalImageUrl = data.url; 
          this.productStateService.addImage(finalImageUrl);
          this.images = this.productStateService.getImages();

          // Emit the updated array to the father component.
          // This is like: 'Hey dad, my pictures array has changed. check if it is still valid to send to the server.'
          this.picturesChange.emit(this.images);
      }, (error) => {
          console.error("Cloudinary not uploaded image...", error);
      });
    } else {
      console.error("Please select a valid image file.");
    }
  }

  triggerFileInput() {
    this.fileInput.nativeElement.click();  
  }

  removeImage(index: number) {
    this.productStateService.removeImage(index);  // Remove the image from state service
    this.images = this.productStateService.getImages();

    // Emit the updated array to the father component.
    // This is like: 'Hey dad, my pictures array has changed. check if it is still valid to send to the server.'
    this.picturesChange.emit(this.images);
  }
}
