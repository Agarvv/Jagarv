import { Component } from '@angular/core';
import { CreateProductServiceStateService } from '../../../state/product/create-product-service-state.service';

@Component({
  selector: 'app-create-product-images',
  templateUrl: './create-product-images.component.html',
  styleUrls: ['./create-product-images.component.css']
})
export class CreateProductImagesComponent {
  images: any[] = [];  // Array to store image URLs

  constructor(private productStateService: CreateProductServiceStateService) {
    this.images = this.productStateService.getImages();  // Get the images from the state service
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];  
    if (file) {
      const reader = new FileReader();  // Create a FileReader to read the file
      reader.onload = (e: any) => {
        this.productStateService.addImage(
            {
                file: file,
                fileSrc: e.target.result
            }
            );  // Ads the image Object to the state service, Object's fileSrc will be used on the HTML to show the image and Object's file will be sendt to the server.
            
            
        this.images = this.productStateService.getImages();  // Update the images array
        console.log("Image Uploaded, here is the new image array!", this.images);
      };
      reader.readAsDataURL(file);  // Convert the image file to a base64 string
    }
  }

  removeImage(index: number) {
    this.productStateService.removeImage(index);  // Remove the image from state service
    this.images = this.productStateService.getImages();  // Update the images array
  }
}
