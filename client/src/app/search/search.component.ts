import { Component, OnInit } from '@angular/core';
import { SearchService } from '@services/search-page/search/search.service';
import { ActivatedRoute } from '@angular/router';
import { finalize } from 'rxjs';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs'; 
import { setLoading, setError, clearMessages } from '@store/admin/admin.actions';
import { setResults } from '@store/search/search.actions';  
import { SearchState } from '@store/search/search.state'; 
import { Product } from '@models/Product'; 

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {

  searchResults$: Observable<Product[]> = new Observable<Product[]>();
  showFilters: boolean = false;  

  constructor(
    private searchService: SearchService,
    public route: ActivatedRoute, 
    private store: Store<{ search: SearchState }>  
  ) {}

  ngOnInit(): void {
    const query = this.route.snapshot.paramMap.get('query');
    this.store.dispatch(clearMessages());
    this.store.dispatch(setLoading({ isLoading: true }));

    if (query) {
      this.searchService.searchProducts(query)
        .pipe(finalize(() => {
          this.store.dispatch(setLoading({ isLoading: false }));
        }))
        .subscribe(
          (data) => {
            console.log('Search Success', data); 
            this.store.dispatch(setResults({ 
              allProducts: data,  
              filteredResults: data
            }));
          },
          (error) => {
            console.error("Search failure", error); 
            this.store.dispatch(setError({ errorMessage: "Oops, something went wrong..." }));
          }
        );
    } else {
      console.error("No query found"); 
    }

    this.searchResults$ = this.store.select(state => state.search.filteredResults); 
  }

  onShowFilters(): void {
    this.showFilters = !this.showFilters;
    console.log('event received') 
  } 
}