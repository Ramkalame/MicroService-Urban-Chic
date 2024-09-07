import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SellerLoginComponent } from '../../auth/components/login/seller-login/seller-login.component';
import { AuthServiceService } from '../../auth/services/auth.service';

export const sellerAuthGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const authService = inject(AuthServiceService);
  let loggedIn = false;
  authService.isLoggedIn().subscribe({
    next: (isLoggedIn) => loggedIn = isLoggedIn,
    error: (error) => console.error('Error:', error)
  })
  if(loggedIn) {
    return true;
  }
  else {
    router.navigate(['/auth/login/seller']);
    return false;
  }
};
