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


const routes: Routes = [

  {
    path: '',
    component: HomeComponent
  },

  {
    path: 'admin',
    component: AdminComponent,
    children: [
      {
        path: '',
        component: AdminDashboardComponent
      },
      {
        path: 'users',
        component: AdminUsersComponent
      },
      {
        path: 'orders',
        component: AdminOrdersComponent
      },
      {
        path: 'sales',
        component: AdminSalesComponent
      },
      {
        path: 'products',
        component: AdminProductsComponent
      },
      {
          path: 'createProduct',
          component: CreateProductComponent
      },
      {
        path: 'editProduct/:productId',
        component: EditProductComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
