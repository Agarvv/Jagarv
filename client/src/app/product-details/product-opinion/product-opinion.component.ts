import { Component, Input } from '@angular/core';

import { ProductOpinion } from '@models/ProductOpinion'

@Component({
  selector: 'app-product-opinion',
  templateUrl: './product-opinion.component.html',
  styleUrl: './product-opinion.component.css'
})
export class ProductOpinionComponent {
  @Input() opinion: ProductOpinion | null = null; 
}
