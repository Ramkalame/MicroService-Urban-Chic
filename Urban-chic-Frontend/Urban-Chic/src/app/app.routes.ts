import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ContactComponent } from './components/contact/contact.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';

export const routes: Routes = [
   
    {
        path: '',
        component:HomeComponent
    },
    {
        path:'login',
        component:LoginComponent
    },
    {
        path:'contact',
        component:ContactComponent
    },
    {
        path:'logout',
        component:LogoutComponent
    },
    {
        path:'register',
        component:RegisterComponent
    },
    {
        path:'seller',
        loadChildren:()=>import('./seller/seller.module').then(m=>m.SellerModule)
    },
   
   
    
];
