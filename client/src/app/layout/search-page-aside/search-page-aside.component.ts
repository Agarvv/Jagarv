import { Component, OnInit } from '@angular/core';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { SearchState } from '@store/search/search.state';
import { Product } from '@models/Product';
import { Filters } from '@models/search/Filters';
import { FiltersService } from '@services/search-page/filters/filters.service'
import { setResults } from '@store/search/search.actions';

@Component({
  selector: 'app-search-page-aside',
  templateUrl: './search-page-aside.component.html',
  styleUrls: ['./search-page-aside.component.css']
})
export class SearchPageAsideComponent implements OnInit {
  searchResults$: Observable<Product[]> = new Observable<Product[]>();

  filters: Filters = {
    category: '',
    minPrice: null,
    maxPrice: null,
    ram: null,
    storage: null,
    color: null,
  };

  constructor(private filtersService: FiltersService, private store: Store<{ search: SearchState }>) {}

  ngOnInit(): void {
    this.searchResults$ = this.store.select(state => state.search.results);
  }

  applyFilters() {
    this.searchResults$.subscribe((products) => {

      const filteredResults = this.filtersService.applyFilters(products, this.filters);

      console.log(filteredResults); // debug

      this.store.dispatch(
        setResults({
          results: filteredResults
        })
        
      )

    });
  }
}
