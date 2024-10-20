import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CreateProductServiceStateService {
  private images: any[] = [];
  constructor() { }
  
  addImage(image: any) {
      this.images.push(image);
  }
  
  removeImage(index: number) {
      this.images.splice(index, 1);
  }
  
  getImages(): any[] {
      return this.images;
  }
}
