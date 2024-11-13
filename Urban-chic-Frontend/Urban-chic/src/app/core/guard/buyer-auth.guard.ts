import { HttpErrorResponse, HttpHeaders, HttpEventType } from '@angular/common/http';
import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { SnackbarService } from '../../common/services/snackbar.service';
import { BuyerAuthService } from '../services/buyer-auth.service';

export const buyerAuthGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const buyerAuthService = inject(BuyerAuthService);
  const snackBar = inject(SnackbarService);
  if(buyerAuthService.isLoggedIn() && buyerAuthService.isAuthorized()){
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
    router.navigate(['/auth/login']);
    return false;
  }
};
