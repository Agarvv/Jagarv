import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateProductServiceStateService {
  private images: string[] = [];
  private imagesSubject = new BehaviorSubject<string[]>(this.images);

  images$ = this.imagesSubject.asObservable(); 

  constructor() {}

  addImage(image: string) {
    console.log("Pushing image", image);
    this.images.push(image);
    this.imagesSubject.next(this.images); 
  }
  
  removeImage(index: number) {
    this.images.splice(index, 1);
    this.imagesSubject.next(this.images); 
  }
  
  getImages(): string[] {
    return this.images;
  }
  
  resetImages(): void {
    this.images = [];
    this.imagesSubject.next(this.images); 
  }
}
