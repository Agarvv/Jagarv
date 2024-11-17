
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';  
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
import { AdminProductFormComponent } from './admin/admin-product-form/admin-product-form.component'; 
import { StoreModule } from '@ngrx/store';
import { ProductImagesFormComponent } from './admin/admin-product-form/product-images-form/product-images-form.component'; 
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ErrorComponentComponent } from './layout/error-component/error-component.component';
import { SuccessComponentComponent } from './layout/success-component/success-component.component';
import { LoadingComponentComponent } from './layout/loading-component/loading-component.component'
import { adminReducer } from './store/admin/admin.reducer';
import { ViewProductButtonComponent } from './admin/admin-products/view-product-button/view-product-button.component';
import { EditProductButtonComponent } from './admin/admin-products/edit-product-button/edit-product-button.component';
import { FeatureProductButtonComponent } from './admin/admin-products/feature-product-button/feature-product-button.component';
import { DeleteProductButtonComponent } from './admin/admin-products/delete-product-button/delete-product-button.component';
import { SpinnerComponentComponent } from './layout/spinner-component/spinner-component.component';
import { CreateProductComponent } from './admin/create-product/create-product.component';
import { EditProductComponent } from './admin/edit-product/edit-product.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { SubmitRegisterButtonComponent } from './auth/register/submit-register-button/submit-register-button.component';
import { LoginSubmitButtonComponent } from './auth/login/login-submit-button/login-submit-button.component';
import { GoogleAuthButtonComponent } from './auth/google-auth-button/google-auth-button.component';
import { GithubAuthButtonComponent } from './auth/github-auth-button/github-auth-button.component';
import { TwitterAuthButtonComponent } from './auth/twitter-auth-button/twitter-auth-button.component';

import { SocialLoginModule, SocialAuthServiceConfig } from '@abacritt/angularx-social-login';
import { GoogleLoginProvider } from '@abacritt/angularx-social-login';
import { environment } from './environments/environment';
import { ResetPasswordComponent } from './auth/reset-password/reset-password.component';
import { SendResetPasswordComponent } from './auth/send-reset-password/send-reset-password.component';
import { ResetPasswordButtonComponent } from './auth/reset-password/reset-password-button/reset-password-button.component';
import { SendResetPasswordButtonComponent } from './auth/send-reset-password/send-reset-password-button/send-reset-password-button.component';
// import { withCredentialsInterceptor } from './interceptors/with-credentials.interceptor';
import { AdminDashboardChartComponent } from './admin/admin-dashboard/admin-dashboard-chart/admin-dashboard-chart.component';
import { AdminDashboardInsightsComponent } from './admin/admin-dashboard/admin-dashboard-insights/admin-dashboard-insights.component';
import { AdminDashboardTopProductsComponent } from './admin/admin-dashboard/admin-dashboard-top-products/admin-dashboard-top-products.component';
import { AddProductVariantComponent } from './admin/add-product-variant/add-product-variant.component';
import { ProductVariantSubmitButtonComponent } from './admin/add-product-variant/product-variant-submit-button/product-variant-submit-button.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductDetailsImagesComponent } from './product-details/product-details-images/product-details-images.component';
import { ProductDetailsDataComponent } from './product-details/product-details-data/product-details-data.component';
import { AddToCartButtonComponent } from './product-details/add-to-cart-button/add-to-cart-button.component';
import { BuyNowButtonComponent } from './product-details/buy-now-button/buy-now-button.component';
import { ProductOpinionComponent } from './product-details/product-opinion/product-opinion.component';
import { CreateProductOpinionComponent } from './product-details/create-product-opinion/create-product-opinion.component';
import { LayoutComponentComponent } from './layout-component/layout-component.component'; 
import { productDetailsReducer } from '@store/cart/product-details.reducer';
import { ProductDetailsDataAttributesComponent } from './product-details/product-details-data/product-details-data-attributes/product-details-data-attributes.component';
import { CreateOpinionSubmitComponent } from './product-details/create-product-opinion/create-opinion-submit/create-opinion-submit.component';
import { CartComponent } from './cart/cart.component';
import { CartProductComponent } from './cart/cart-product/cart-product.component';
import { RemoveCartProductComponent } from './cart/cart-product/remove-cart-product/remove-cart-product.component';
import { CartTicketComponent } from './cart/cart-ticket/cart-ticket.component';
import { CheckoutButtonComponent } from './cart/cart-ticket/checkout-button/checkout-button.component';
import { ProductsByCategoryComponent } from './products-by-category/products-by-category.component';
import { ProductCardComponent } from './layout/product-card/product-card.component';
import { PaymentComponent } from './payment/payment.component';
import { SearchComponent } from './search/search.component';
import { SearchPageAsideComponent } from './layout/search-page-aside/search-page-aside.component';
import { ProductListComponent } from './layout/product-list/product-list.component';
import { searchReducer } from '@store/search/search.reducer'

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
    AdminProductFormComponent,
    ProductImagesFormComponent,
    ErrorComponentComponent,
    SuccessComponentComponent,
    LoadingComponentComponent,
    ViewProductButtonComponent,
    EditProductButtonComponent,
    FeatureProductButtonComponent,
    DeleteProductButtonComponent,
    SpinnerComponentComponent,
    CreateProductComponent,
    EditProductComponent,
    LoginComponent,
    RegisterComponent,
    SubmitRegisterButtonComponent,
    LoginSubmitButtonComponent,
    GoogleAuthButtonComponent,
    GithubAuthButtonComponent,
    TwitterAuthButtonComponent,
    ResetPasswordComponent,
    SendResetPasswordComponent,
    ResetPasswordButtonComponent,
    SendResetPasswordButtonComponent,
    AdminDashboardChartComponent,
    AdminDashboardInsightsComponent,
    AdminDashboardTopProductsComponent,
    AddProductVariantComponent,
    ProductVariantSubmitButtonComponent,
    ProductDetailsComponent,
    ProductDetailsImagesComponent,
    ProductDetailsDataComponent,
    AddToCartButtonComponent,
    BuyNowButtonComponent,
    ProductOpinionComponent,
    CreateProductOpinionComponent,
    LayoutComponentComponent,
    ProductDetailsDataAttributesComponent,
    CreateOpinionSubmitComponent,
    CartComponent,
    CartProductComponent,
    RemoveCartProductComponent,
    CartTicketComponent,
    CheckoutButtonComponent,
    ProductsByCategoryComponent,
    ProductCardComponent,
    PaymentComponent,
    SearchComponent,
    SearchPageAsideComponent,
    ProductListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    StoreModule.forRoot({ 
        admin: adminReducer,
        productDetails: productDetailsReducer,
        search: searchReducer
    }),
    SocialLoginModule 
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              environment.googleClientId
            )
          }
        ]
      } as SocialAuthServiceConfig,
    }
 //   {
    //  provide: HTTP_INTERCEPTORS, 
    //  useClass: withCredentialsInterceptor, 
   //   multi: true
 //   }
  ],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA] 
})
export class AppModule { }