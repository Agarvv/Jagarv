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
import { CreateProductButtonComponent } from './admin/admin-create-product/create-product-button/create-product-button.component';
import { CreatePrproductImagesComponent } from './admin/admin-create-product/create-prproduct-images/create-prproduct-images.component';
import { CreateProductImagesComponent } from './admin/admin-create-product/create-product-images/create-product-images.component';


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
    CreateProductButtonComponent,
    CreatePrproductImagesComponent,
    CreateProductImagesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    StoreModule.forRoot({}, {})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
