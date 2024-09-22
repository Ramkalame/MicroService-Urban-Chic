import { Routes } from '@angular/router';
import { SellerDocumentsComponent } from './seller/components/seller-documents/seller-documents.component';
import { SellerDashboardComponent } from './seller/components/seller-dashboard/seller-dashboard.component';
import { ProductManagementComponent } from './seller/components/product-management/product-management.component';
import { OrderManagementComponent } from './seller/components/order-management/order-management.component';
import { DashboardHomeComponent } from './seller/components/dashboard-home/dashboard-home.component';
import { SellerProfileComponent } from './seller/components/seller-profile/seller-profile.component';
import { SellerRegistrationComponent } from './auth/components/register/seller-registration/seller-registration.component';
import { SellerLoginComponent } from './auth/components/login/seller-login/seller-login.component';
import { sellerAuthGuard } from './core/guard/seller-auth.guard';
import { AddProductComponent } from './seller/components/add-product/add-product.component';
import { HomeComponent } from './buyer/components/home/home.component';
import { BuyerProfileComponent } from './buyer/components/buyer-profile/buyer-profile.component';
import { ShowProductComponent } from './buyer/components/show-product/show-product.component';
import { CartComponent } from './buyer/components/cart/cart.component';
import { FavouriteComponent } from './buyer/components/favourite/favourite.component';

export const routes: Routes = [
  { path: 'auth/register/seller', component: SellerRegistrationComponent },
  { path: 'auth/login/seller', component: SellerLoginComponent },
  {
    path: 'seller/documents',
    component: SellerDocumentsComponent,
    canActivate: [sellerAuthGuard],
  },
  //defining route for the seller dashboard component.
  {
    path: 'seller/dashboard',
    component: SellerDashboardComponent,
    // canActivate: [sellerAuthGuard],
    children: [
      //child route to be displayed in the seller dashboard component.
      { path: 'products', component: ProductManagementComponent },
      { path: 'orders', component: OrderManagementComponent },
      { path: 'home', component: DashboardHomeComponent },
      { path: 'profile', component: SellerProfileComponent },
      { path: 'add-product', component: AddProductComponent }
    ],
  },


  //buyer routing
  {path: '', component: HomeComponent},
  { path: '', component: ShowProductComponent },
  { path: 'profile', component: BuyerProfileComponent },
  { path: 'viewCart', component: CartComponent },
  { path: 'favorites', component: FavouriteComponent }


];
