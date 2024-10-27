import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CreateProductServiceStateService {
  private images: string[] = [];

  constructor() { }
  
  addImage(image: string) {
      //Just for check the image.
      console.log("Pushing image", image)
      this.images.push(image);
  }
  
  removeImage(index: number) {
      this.images.splice(index, 1);
  }
  
  getImages(): string[] {
      return this.images;
  }
  
  resetImages(): void {
     this.images = [];
  }
}
