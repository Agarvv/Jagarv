import { Component, OnInit } from '@angular/core';
import { SearchService } from "@services/search-page/search/search.service"
import { ActivatedRoute } from "@angular/router"; 
import { finalize } from "rxjs";
import { Store } from '@ngrx/store';
import { setLoading, setError, clearMessages } from '@store/admin/admin.actions';


@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  constructor
  (
      private searchService: SearchService,
      private route: ActivatedRoute,
      private store: Store 
  ) {} 
  
  ngOnInit(): void {
    const query = this.route.snapshot.paramMap.get('query');
    this.store.dispatch(clearMessages());
    this.store.dispatch(setLoading({ isLoading: true }));

    if(query) {
        this.searchService.searchProducts(query)
      .pipe(finalize(() => {
          this.store.dispatch(setLoading({ isLoading: false }));
      }))
      .subscribe(
        (data) => {
          console.log('Search Success', data); // debug
        },
        (error) => {
          console.error("Search failure", error); // debug
          this.store.dispatch(setError({ errorMessage: "Oops, something went wrong..." }));
        }
      );
    } else {
        console.error("Not query found"); // debug
    }
      
 }
  
  
}
