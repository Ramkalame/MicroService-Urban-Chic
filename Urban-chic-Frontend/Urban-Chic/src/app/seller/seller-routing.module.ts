import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SellerDashboardComponent } from './components/seller-dashboard/seller-dashboard.component';
import { SellerLoginComponent } from './components/seller-login/seller-login.component';
import { SellerRegisterComponent } from './components/seller-register/seller-register.component';

const routes: Routes = [
  {
    path:'register',
    component:SellerRegisterComponent
  },
  {
    path:'login',
    component:SellerLoginComponent
  },

  {
    path:'dashboard',
    component:SellerDashboardComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SellerRoutingModule { }
