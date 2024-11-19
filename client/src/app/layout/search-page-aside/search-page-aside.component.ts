import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { SearchState } from '@store/search/search.state'; 
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

  constructor(private store: Store<{ search: SearchState }>) {}

  ngOnInit(): void {
    this.searchResults$ = this.store.select(state => state.search.results);
  }

  applyFilters() {

  }
}