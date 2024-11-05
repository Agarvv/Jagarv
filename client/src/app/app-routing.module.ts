import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent  } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component'
import { AdminUsersComponent } from './admin/admin-users/admin-users.component'
import { AdminOrdersComponent } from './admin/admin-orders/admin-orders.component'
import { AdminSalesComponent } from './admin/admin-sales/admin-sales.component'
import { AdminProductsComponent } from './admin/admin-products/admin-products.component'
import { AdminProductFormComponent } from './admin/admin-product-form/admin-product-form.component'; 
import { EditProductComponent } from './admin/edit-product/edit-product.component';
import { CreateProductComponent } from './admin/create-product/create-product.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { ResetPasswordComponent } from './auth/reset-password/reset-password.component'; 
import { SendResetPasswordComponent } from './auth/send-reset-password/send-reset-password.component'


const routes: Routes = [

  // login page 
  {
    path: 'login',
    component: LoginComponent // the login page for the users
  },
  
  // the homepage 
  {
    path: '',
    component: HomeComponent
  },
 
  // registration page 
  {
    path: 'register',
    component: RegisterComponent 
  },
  
  // reset password receive mail page
  {
      path: 'send-reset-password',
      component: SendResetPasswordComponent
  },
  
  // reset password page 
  {
      path: 'reset-password/:email/:token',
      component: ResetPasswordComponent 
  },
  
  // the admin page
  {
    path: 'admin',
    component: AdminComponent,
    children: [
      {
        path: '',
        component: AdminDashboardComponent // the dashboard for the admins
      },
      {
        path: 'users',
        component: AdminUsersComponent // the users page for the admins
      },
      {
        path: 'orders',
        component: AdminOrdersComponent // the orders page for the admins
      },
      {
        path: 'sales',
        component: AdminSalesComponent // the sales page for the admins
      },
      {
        path: 'products',
        component: AdminProductsComponent // the products page for the admins
      },
      {
          path: 'createProduct',
          component: CreateProductComponent // the create product page for the admins
      },
      {
        path: 'editProduct/:productId',
        component: EditProductComponent // the edit product page for the admins
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
