import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent  } from './home/home.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  // THIS IS THE ROUTE FOR THE HOMEPAGE.
  {
    path: '',
    component: HomeComponent
  },
  // THIS IS THE ROUTE FOR THE ADMIN PAGE.
  {
    path: 'admin',
    component: AdminComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
