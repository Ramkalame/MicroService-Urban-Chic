import { Routes } from '@angular/router';
import { SellerRegistrationComponent } from './features/auth/register/seller-registration/seller-registration.component';
import { SellerDocumentsComponent } from './features/seller/components/seller-documents/seller-documents.component';
import { SellerLoginComponent } from './features/auth/login/seller-login/seller-login.component';

export const routes: Routes = [
    {path:'auth/register/seller',component:SellerRegistrationComponent},
    {path:'seller/documents',component:SellerDocumentsComponent},
    {path:'auth/login/seller',component:SellerLoginComponent}
];
