import { Component, Input, Output, EventEmitter } from '@angular/core';


@Component({
  selector: 'app-search-page-header',
  templateUrl: './search-page-header.component.html',
  styleUrl: './search-page-header.component.css'
})
export class SearchPageHeaderComponent {
 @Input() searchQ: string  | null = "";
 @Output() showFilters: EventEmitter<void> = new EventEmitter();

 
 constructor() {} 

 showFiltersAside() {
  this.showFilters.emit();
 }
}
