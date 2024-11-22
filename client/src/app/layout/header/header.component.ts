import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
 searchQ: string = ""; 

 constructor(private router: Router) {}

 handleSearch(): void {
  this.router.navigate(['/search', this.searchQ]);
 }
}
