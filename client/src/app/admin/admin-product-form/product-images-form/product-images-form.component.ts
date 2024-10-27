import { Component, ViewChild, ElementRef, Output, EventEmitter, OnInit, OnDestroy } from '@angular/core';
import { CreateProductServiceStateService } from '../../../state/admin/product/create-product-service-state.service';
import { MediaServiceService } from '../../../services/media/media-service.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product-images-form',
  templateUrl: './product-images-form.component.html',
  styleUrls: ['./product-images-form.component.css']
})
export class ProductImagesFormComponent implements OnInit, OnDestroy {
  images: string[] = [];  
  private imagesSubscription!: Subscription; 
  @ViewChild('fileInput') fileInput!: ElementRef; 

  @Output() picturesChange = new EventEmitter<string[]>();

  constructor(
    private productStateService: CreateProductServiceStateService,
    private mediaService: MediaServiceService
  ) {}

  ngOnInit(): void {
    this.imagesSubscription = this.productStateService.images$.subscribe((images) => {
      this.images = images;
      this.picturesChange.emit(this.images);
    });
  }

  ngOnDestroy(): void {
    if (this.imagesSubscription) {
      this.imagesSubscription.unsubscribe();
    }
  }

  onFileSelected(event: any) {
    const file = event.target.files[0];  
    if (file && file.type.startsWith('image/')) {
      this.mediaService.uploadProductImage(file).subscribe((data) => {
          const finalImageUrl = data.url; 
          this.productStateService.addImage(finalImageUrl);
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
    this.productStateService.removeImage(index);
  }
}
