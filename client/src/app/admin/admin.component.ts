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
  
}