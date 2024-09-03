import { Routes } from '@angular/router';
import { SellerRegistrationComponent } from './auth/register/seller-registration/seller-registration.component';
import { SellerDocumentsComponent } from './seller/components/seller-documents/seller-documents.component';
import { SellerLoginComponent } from './auth/login/seller-login/seller-login.component';
import { SellerDashboardComponent } from './seller/components/seller-dashboard/seller-dashboard.component';
import { ProductManagementComponent } from './seller/components/product-management/product-management.component';

export const routes: Routes = [
    {path:'auth/register/seller',component:SellerRegistrationComponent},
    {path:'seller/documents',component:SellerDocumentsComponent},
    {path:'auth/login/seller',component:SellerLoginComponent},
    {path:'seller/dashboard',component:SellerDashboardComponent},
    {path:'seller/dashboard/products',component:ProductManagementComponent}
];
