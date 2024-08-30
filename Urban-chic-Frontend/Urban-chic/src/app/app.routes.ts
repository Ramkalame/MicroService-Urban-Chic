import { Routes } from '@angular/router';
import { SellerRegistrationComponent } from './features/auth/register/seller-registration/seller-registration.component';
import { SellerDocumentsComponent } from './features/seller/components/seller-documents/seller-documents.component';

export const routes: Routes = [
    {path:'seller/register',component:SellerRegistrationComponent},
    {path:'seller/register/documents',component:SellerDocumentsComponent}
];
