import { Component, OnInit } from '@angular/core';
import { PublicProductsService } from '@services/products/products.service'
import { BestSeller } from '@models/product/BestSeller'


@Component({
  selector: 'app-best-sellers',
  templateUrl: './best-sellers.component.html',
  styleUrl: './best-sellers.component.css'
})
export class BestSellersComponent implements OnInit {
  bestSellers: BestSeller[] = []; 
  
  constructor(private productsService: PublicProductsService) {
      
  }
  
  ngOnInit(): void {
      this.productsService.getBestSellers()
      .pipe(
         
      )
      .subscribe((data: BestSeller[]) => {
           console.log("best sellers", data)
           this.bestSellers = data; 
      }, (error: any) => {
          console.error(error)
      })
  }
}
