import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
// i know the name is wrong, but im not going to change it, to lazy :p
export class MediaServiceService {

  private cloudinaryUrl = `https://api.cloudinary.com/v1_1/${environment.cloudinary.cloud_name}/image/upload`;

  constructor(private http: HttpClient) { }

  uploadProductImage(image: File): Observable<any> {
    const formData = new FormData();
    formData.append('file', image);
    formData.append('upload_preset', environment.cloudinary.upload_preset);

    return this.http.post(this.cloudinaryUrl, formData);
  }
}