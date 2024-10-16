import { Component } from '@angular/core';

@Component({
  selector: 'app-admin-aside',
  templateUrl: './admin-aside.component.html',
  styleUrl: './admin-aside.component.css'
})
//Im doing a separate Aside Component for the Admin-Page.
//Like that, the admins will have a diferent aside for them.

export class AdminAsideComponent {
 isCollapsed = true;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }
}
