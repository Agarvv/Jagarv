import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AppState } from './store'; 
import { clearMessages } from './store/admin/admin.actions'; 

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  loading$: Observable<boolean>;
  success$: Observable<string | null>;
  error$: Observable<string | null>;

  constructor(private store: Store<AppState>) {
    this.loading$ = this.store.pipe(select('loading'));
    this.success$ = this.store.pipe(select('success'));
    this.error$ = this.store.pipe(select('error'));
  }

  ngOnInit(): void {
    console.log('APP OK');
  }

  clearMessages(): void {
    this.store.dispatch(clearMessages());
  }
}