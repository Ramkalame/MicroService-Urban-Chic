import { inject } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivateFn, Router, RouterStateSnapshot } from '@angular/router';
import { JwtDecoderService } from '../services/jwt-decoder.service';
import { SellerAuthService } from '../services/seller-auth.service';
import { SnackbarService } from '../../common/services/snackbar.service';
import { HttpErrorResponse, HttpEventType, HttpHeaders } from '@angular/common/http';

export const sellerAuthGuard: CanActivateFn = (route, state) => {

  const router = inject(Router);
  const sellerAuthService = inject(SellerAuthService);
  const snackBar = inject(SnackbarService);
  if(sellerAuthService.isLoggedIn() && sellerAuthService.isAuthorized()){
    // User is authenticated and authorized, allow navigation
    return true;
  }else{
    let error:HttpErrorResponse = {
      status: 401,
      statusText: 'Unauthorized',
      error: 'User not authorized',
      name: 'HttpErrorResponse',
      message: 'Please Login',
      ok: false,
      headers: new HttpHeaders,
      url: null,
      type: HttpEventType.ResponseHeader
    }
    snackBar.openNonApiFailedSnackBar(error);
    router.navigate(['/auth/login/seller']);
    return false;
  }
};
