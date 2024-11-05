import { Component, OnInit } from '@angular/core';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AdminState } from './store/admin/admin.state'; 
import { clearMessages } from './store/admin/admin.actions'; 
import { selectLoading, selectError, selectSuccess } from './store/admin/admin.selectors';
import { Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'] 
})
export class AppComponent implements OnInit {
  loading$: Observable<boolean>;
  success$: Observable<string | null>;
  error$: Observable<string | null>;

  constructor(private store: Store<AdminState>, private router: Router) {
    this.loading$ = this.store.pipe(select(selectLoading));
    this.success$ = this.store.pipe(select(selectSuccess));
    this.error$ = this.store.pipe(select(selectError));
  }

  clearMessages(): void {
    this.store.dispatch(clearMessages());
  }

  ngOnInit(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe((event: NavigationEnd) => {
          this.store.dispatch(clearMessages());
      });
  }

  onActivate(event: any) {
    this.clearMessages();
  }

}