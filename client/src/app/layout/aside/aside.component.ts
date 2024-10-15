import { Component } from '@angular/core';

@Component({
  selector: 'app-aside',
  templateUrl: './aside.component.html',
  styleUrl: './aside.component.css'
})
export class AsideComponent {
  isCollapsed = true;
  isSubmenuOpen = false;

  toggleSidebar() {
    this.isCollapsed = !this.isCollapsed;
  }

  toggleSubmenu() {
    this.isSubmenuOpen = !this.isSubmenuOpen;
  }
}
