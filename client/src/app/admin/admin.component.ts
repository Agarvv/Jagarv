import { Component } from '@angular/core';
import { AdminStateManagerService } from '../state/admin/admin-state-manager.service';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs';
import { AdminState } from '../store/admin/admin.state';
import { clearMessages } from '../store/admin/admin.actions'; 

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [AdminStateManagerService]  
})
export class AdminComponent {
  // This will handle our error, success and loading state in a cleaner manner,
  // we dont have to handle it on our 500 admin's components.

  //loading$: Observable<boolean>;
  //success$: Observable<string | null>;
  //error$: Observable<string | null>;
  
 // constructor(private store: Store<{ admin: AdminState }>) {
  //  this.loading$ = this.store.select(state => state.admin.loading);
  //  this.success$ = this.store.select(state => state.admin.success);
  //  this.error$ = this.store.select(state => state.admin.error);
  //}

  //onActivate(component: any) {
   // this.store.dispatch(clearMessages());
 // }
}