import { Component, ViewChild, ElementRef, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { CreateProductServiceStateService } from '../../../state/admin/product/create-product-service-state.service';
import { MediaServiceService } from '../../../services/media/media-service.service';
import { Subscription } from 'rxjs';

// This component is the child of our admin-product-form component, 
// and shows the demo-images that our admins selected to create, or edit a product.
@Component({
  selector: 'app-product-images-form',
  templateUrl: './product-images-form.component.html',
  styleUrls: ['./product-images-form.component.css']
})
export class ProductImagesFormComponent implements OnInit, OnDestroy {
  images: string[] = []; // here will be stored the images that we get from our state service
  private imagesSubscription!: Subscription; 
  @ViewChild('fileInput') fileInput!: ElementRef; 

  @Output() picturesChange = new EventEmitter<string[]>();

  constructor(
    private productStateService: CreateProductServiceStateService,
    private mediaService: MediaServiceService
  ) {}

  ngOnInit(): void {
    // search for the images on our state service and subscribes to that array
    this.imagesSubscription = this.productStateService.images$.subscribe((images) => {
      this.images = images;
      this.picturesChange.emit(this.images);
    });
  }
  
  // unsubscribe from the subscription when the component is destroyed to prevent memory leaks
  ngOnDestroy(): void {
    if (this.imagesSubscription) {
      this.imagesSubscription.unsubscribe();
    }
  }
  
  // this method triggers when the user selects an image from their file system
  onFileSelected(event: any) {
    const file = event.target.files[0]; // takes the file selected,
    if (file && file.type.startsWith('image/')) { // if it is a image, proceeds
      this.mediaService.uploadProductImage(file).subscribe((data) => { // uploads the image to cloudinary
          const finalImageUrl = data.url; // takes the url of the now cloudinary-uploaded image
          this.productStateService.addImage(finalImageUrl); // and adds it to our state service
      }, (error) => {
          console.error("Cloudinary not uploaded image...", error); // if something does not work, we debug it
      });
    } else {
      return // if is not a image, just exits.
    }
  }
  
  // this method triggers when the user clicks on the "Add Image" button
  triggerFileInput() {
    this.fileInput.nativeElement.click();  
  }
  
  // this method triggers when the user clicks on the "Remove Image" button for a given index
  removeImage(index: number) {
    this.productStateService.removeImage(index);
  }
}
