import { Component } from '@angular/core';
import { AdminStateManagerService } from '../state/admin/admin-state-manager.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
  providers: [AdminStateManagerService]  
})
export class AdminComponent {
  constructor(public adminStateManager: AdminStateManagerService) {}

  onActivate(component: any) {
    if (component.statusUpdate) {
      component.statusUpdate.subscribe((status: { isLoading: boolean, errorMessage: string | null, successMessage: string | null }) => {
        this.adminStateManager.setLoading(status.isLoading);
        if (status.errorMessage) {
          this.adminStateManager.setError(status.errorMessage);
        } else {
          this.adminStateManager.clearMessages();
        }
        if (status.successMessage) {
          this.adminStateManager.setSuccess(status.successMessage);
        }
      });
    }
  }
}