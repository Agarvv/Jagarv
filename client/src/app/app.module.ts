import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';  
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { AdminComponent } from './admin/admin.component';
import { HeaderComponent } from './layout/header/header.component';
import { AsideComponent } from './layout/aside/aside.component';
import { FooterComponent } from './layout/footer/footer.component';
import { HomeMainComponent } from './home/home-main/home-main.component';
import { AdminAsideComponent } from './layout/admin-aside/admin-aside.component';
import { AdminUsersComponent } from './admin/admin-users/admin-users.component';
import { AdminSalesComponent } from './admin/admin-sales/admin-sales.component';
import { AdminProductsComponent } from './admin/admin-products/admin-products.component';
import { AdminOrdersComponent } from './admin/admin-orders/admin-orders.component';
import { AdminDashboardComponent } from './admin/admin-dashboard/admin-dashboard.component';
import { AdminCreateProductComponent } from './admin/admin-create-product/admin-create-product.component';
import { StoreModule } from '@ngrx/store';
import { CreateProductImagesComponent } from './admin/admin-create-product/create-product-images/create-product-images.component';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { ErrorComponentComponent } from './layout/error-component/error-component.component';
import { SuccessComponentComponent } from './layout/success-component/success-component.component';
import { LoadingComponentComponent } from './layout/loading-component/loading-component.component'
import { adminReducer } from './store/admin/admin.reducer';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    AdminComponent,
    HeaderComponent,
    AsideComponent,
    FooterComponent,
    HomeMainComponent,
    AdminAsideComponent,
    AdminUsersComponent,
    AdminSalesComponent,
    AdminProductsComponent,
    AdminOrdersComponent,
    AdminDashboardComponent,
    AdminCreateProductComponent,
    CreateProductImagesComponent,
    CreateProductImagesComponent,
    ErrorComponentComponent,
    SuccessComponentComponent,
    LoadingComponentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    StoreModule.forRoot({ admin: adminReducer}),

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
