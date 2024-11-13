import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-create-product-opinion',
  templateUrl: './create-product-opinion.component.html',
  styleUrls: ['./create-product-opinion.component.css']
})
export class CreateProductOpinionComponent {
  opinionForm: FormGroup;
  
  constructor(private fb: FormBuilder, private route: ActivatedRoute) {
      const productId = Number(this.route.snapshot.paramMap.get('productId')) || null; 
      
      this.opinionForm = this.fb.group({
          content: ['', Validators.required],
          productId: [productId, Validators.required]
      });
      
      
  }
}