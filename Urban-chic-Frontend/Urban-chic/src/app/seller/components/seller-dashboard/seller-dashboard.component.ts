import { MediaMatcher } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, computed, inject, signal, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTooltipModule } from '@angular/material/tooltip';
import { Router, RouterModule } from '@angular/router';
import { SnackbarService } from '../../../common/services/snackbar.service';
import { AuthServiceService } from '../../../auth/services/auth.service';
import { JwtDecoderService } from '../../../core/services/jwt-decoder.service';
import { MatSelectModule } from '@angular/material/select';
import { MatDividerModule } from '@angular/material/divider';
import { SellerAuthService } from '../../../core/services/seller-auth.service';

export type MenuItem = {
  icon: string;
  label: string;
  route: string;
}

@Component({
  selector: 'app-seller-dashboard',
  standalone: true,
  imports: [MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    CommonModule,
    RouterModule, MatTooltipModule,
    MatSelectModule,
    MatDividerModule],
  templateUrl: './seller-dashboard.component.html',
  styleUrl: './seller-dashboard.component.scss'
})
export class SellerDashboardComponent {

  jwtDecoder = inject(JwtDecoderService);
  sellerAuthService = inject(SellerAuthService);
  snackBar = inject(SnackbarService);
  router = inject(Router);
  collapse = signal(false);
  sideNavWidht = computed(() => this.collapse() ? '65px' : '190px');

  menuItems1 = signal<MenuItem[]>([
    { icon: 'dashboard', label: 'Dashboard', route: 'home' },
    { icon: 'chrome_reader_mode', label: 'Orders', route: 'orders' },
    { icon: 'local_mall', label: 'Products', route: 'products' },
    { icon: 'add_box', label: 'Add Product', route: 'add-product' },
    { icon: 'import_export', label: 'Return', route: 'seller/dashboard/products' },
    { icon: 'insert_chart', label: 'Analysis', route: 'seller/dashboard/products' },
    { icon: 'account_balance', label: 'Payments', route: 'seller/dashboard/products' },
  ]);

  menuItems2 = signal<MenuItem[]>([
    { icon: 'account_circle', label: 'Profile', route: 'profile' },
    { icon: 'help', label: 'Help', route: 'seller/dashboard/products' },
  ]);

  constructor() { }

  //logout method
  onLogout() {
    this.sellerAuthService.logout();
    this.snackBar.openSuccessSnackBar('Logged out successfully')
    this.router.navigate(['/auth/login/seller'])
  }



}