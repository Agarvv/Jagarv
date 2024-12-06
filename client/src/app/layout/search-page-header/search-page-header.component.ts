import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-search-page-header',
  templateUrl: './search-page-header.component.html',
  styleUrls: ['./search-page-header.component.css']
})
export class SearchPageHeaderComponent {
  @Input() searchQ: string  | null = "";
  @Output() showFilters: EventEmitter<void> = new EventEmitter();

  constructor(private router: Router) {}

  reloadPage() {
    if (this.searchQ) {
      this.router.navigate(['/search', this.searchQ]).then(() => {
        window.location.reload();  
      });
    }
  }

  showFiltersAside() {
    this.showFilters.emit();
  }
}