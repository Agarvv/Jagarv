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

  onActivate(event: any) {
    this.clearMessages();  // Clear messages when a new component is activated. This will prevent old messages from being displayed when navigating between components.  // For example, in your AdminComponent, you might have a button to edit a product, and when you navigate to a different component, you want to clear the success message.  // The clearMessages() function dispatches the clearMessages action, which clears the state.  // The selectLoading, selectSuccess, and selectError selectors then return the current state values.  // The AppComponent subscribes to these selectors and updates its UI accordingly.  // This ensures that messages are only displayed when they are relevant to the current component.  // This is a simple example, and in a real-world application, you might want to handle messages differently based on the component's route, or based on other criteria.  // In a production application, you would want to implement a more sophisticated system for handling
  }
}