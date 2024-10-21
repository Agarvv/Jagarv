import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class CreateProductServiceStateService {
  private images: any[] = [];
  // Images array should look like:
  //   [
  //     {
  //       fileSrc: (the file's source to showcase      the image on HTML),
  //       file: (the entire file to send to server)
  //     }
  //   ]
  //
  //
  constructor() { }
  
  addImage(image: Object) {
      //Just for check the image structure.
      console.log("Pushing image", image)
      this.images.push(image);
  }
  
  removeImage(index: number) {
      this.images.splice(index, 1);
  }
  
  getImages(): any[] {
      return this.images;
  }
}
