import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-page-header',
  templateUrl: './search-page-header.component.html',
  styleUrl: './search-page-header.component.css'
})
export class SearchPageHeaderComponent {
 @Input() searchQ: string  | null = "";
 
 constructor(private router: Router) {} 

 handleSearch(): void {
   this.router.navigate(['/search', this.searchQ]);
 }
}
