import { Component, ViewChild, ElementRef } from '@angular/core';
import { CreateProductServiceStateService } from '../../../state/product/create-product-service-state.service';
import { MediaServiceService } from '../../../services/media/media-service-service';

@Component({
  selector: 'app-create-product-images',
  templateUrl: './create-product-images.component.html',
  styleUrls: ['./create-product-images.component.css']
})
export class CreateProductImagesComponent {
  images: string[] = [];  // Array to store image URLs
  @ViewChild('fileInput') fileInput!: ElementRef; 

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
          console.log("Cloudinary just uploaded image!", data);
          const finalImageUrl = data.url; 
          this.productStateService.addImage(finalImageUrl);
          this.images = this.productStateService.getImages();
          console.log("Image Uploaded, here is the new image array!", this.images);
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
    this.images = this.productStateService.getImages();  // Update the images array
  }
}