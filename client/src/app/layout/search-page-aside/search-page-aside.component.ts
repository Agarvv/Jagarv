import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { setResults } from '@store/search/search.actions';  
import { Product } from '@models/Product';  

@Component({
  selector: 'app-search-page-aside',
  templateUrl: './search-page-aside.component.html',
  styleUrls: ['./search-page-aside.component.css']
})
export class SearchPageAsideComponent implements OnInit {

  searchResults$: Observable<Product[]> = new Observable<Product[]>();
  
  filters = {
    
  };

  constructor(private store: Store) {}

  ngOnInit(): void {
    this.searchResults$ = this.store.select(state: any => state.search.results);
  }

  applyFilters() {
    
  }

}