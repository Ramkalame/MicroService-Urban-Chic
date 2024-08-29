import { Routes } from '@angular/router';
import { SellerRegistrationComponent } from './features/seller/components/seller-registration/seller-registration/seller-registration.component';
import { SellerDocumentsComponent } from './features/seller/components/seller-registration/seller-documents/seller-documents.component';

export const routes: Routes = [
    {path:'seller/register',component:SellerRegistrationComponent},
    {path:'seller/register/documents',component:SellerDocumentsComponent}
];
