import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AdminState } from './store/admin/admin.state'; 
import { clearMessages } from './store/admin/admin.actions'; 
import { selectLoading, selectError, selectSuccess } from './store/admin/admin.selectors';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  loading$: Observable<boolean>;
  success$: Observable<string | null>;
  error$: Observable<string | null>;

  constructor(private store: Store<AdminState>) {
    this.loading$ = this.store.pipe(select(selectLoading));
    this.success$ = this.store.pipe(select(selectSuccess));
    this.error$ = this.store.pipe(select(selectError));
  }

  ngOnInit(): void {
    console.log(this.error$);
  }

  clearMessages(): void {
    this.store.dispatch(clearMessages());
  }
}