import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { authGuard } from './guards/auth/auth.guard';  
import { LayoutComponentComponent } from './layout-component/layout-component.component'; // i had some serious issues with the component name :P 

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
import { AddProductVariantComponent } from './admin/add-product-variant/add-product-variant.component'
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartComponent } from './cart/cart.component';
import { ProductsByCategoryComponent } from './products-by-category/products-by-category.component';
import { PaymentComponent } from './payment/payment.component';
import { SearchComponent } from './search/search.component'; 


// LayoutComponent 

const routes: Routes = [

  // login page 
  {
    path: 'login',
    component: LoginComponent // the login page for the users
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
  // Search page with his own layout
  
  {
      path: 'search/:query',
      component: SearchComponent
  },
  
  {   // the main layout
      path: '',
      component: LayoutComponentComponent,
      children: [
         {
             // homepage
             path: '',
             component: HomeComponent,
             canActivate: [authGuard]
         },
         {
             // product details page
             path: 'product/:productId',
             component: ProductDetailsComponent
         },
         {
          path: 'cart',
          component: CartComponent
         },
         {
          path: 'category/:category',
          component: ProductsByCategoryComponent
         },
         {
          path: 'pay',
          component: PaymentComponent
         }
      ]
  },
  
  // the admin page with his own layout
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
      },
      {
          path: 'addVariant/:productId',
          component: AddProductVariantComponent // the add product variant for the admins
      }
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
