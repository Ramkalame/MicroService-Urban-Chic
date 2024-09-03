import { MediaMatcher } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, computed, inject, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';

export type MenuItem = {
  icon: string;
  label: string;
  route: string;
}

@Component({
  selector: 'app-seller-dashboard',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule, MatSidenavModule, MatListModule,MatListModule,CommonModule,RouterModule],
  templateUrl: './seller-dashboard.component.html',
  styleUrl: './seller-dashboard.component.css'
})
export class SellerDashboardComponent {

  collapse = signal(false);
  sideNavWidht = computed( () => this.collapse() ? '65px' : '210px');

  menuItems = signal<MenuItem[]>([
    {icon: 'dashboard', label: 'Dashboard', route: 'products'},
    {icon: 'chrome_reader_mode', label: 'Orders', route: 'products'},
    {icon: 'local_mall', label: 'Products', route: 'seller/dashboard/products'},
    {icon: 'import_export',label:'Return', route: 'seller/dashboard/products'},
    {icon: 'insert_chart',label:'Analysis', route: 'seller/dashboard/products'},
    {icon: 'account_balance', label: 'Payments', route: 'seller/dashboard/products'},
    {icon: 'account_circle', label: 'Profile', route: 'seller/dashboard/products'},
    {icon: 'help', label: 'Help', route: 'seller/dashboard/products'},
    {icon: 'exit_to_app', label: 'Logout', route: 'seller/dashboard/products'}
  ]);


}