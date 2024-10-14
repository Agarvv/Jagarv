import { Component, OnInit } from '@angular/core';
import { ProductService } from './services/product/product.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  phrase: string = "Working...";
  data: any; 

  constructor(private productService: ProductService) {}

  ngOnInit(): void {
    this.productService.testApi().subscribe(response => { 
      this.data = response; 
      console.log('Data from Java Server:', this.data); 
    });
  }
}
