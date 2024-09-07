import { inject, Injectable } from '@angular/core';
import { SnackbarService } from '../../common/services/snackbar.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { UserRole } from '../enums/user-roles';

@Injectable({
  providedIn: 'root'
})
export class JwtDecoderService {

  constructor() { }


  public decodeToken(token: string): Observable<any> {
    try {
      if (this.validateTokenFormat(token)) {
        const decodedPayload = JSON.parse(atob(token.split('.')[1].replace('-', '+').replace('_', '/')));
        return of(decodedPayload);
      }
      return of(null);
    } catch (error) {
      return of(null).pipe(
        catchError(() => {
          // Handle error (e.g., display a message)
          console.error('Error decoding token:', error);
          return of(null);
        })
      );
    }
  }

  private validateTokenFormat(token: string): boolean {

    const segments = token.split('.');
    if (segments.length !== 3) {
      return false;
    }
    for (const segement of segments) {
      try {
        atob(segement.replace('-', '+').replace('_', '/'));
      } catch (error) {
        return false;
      }
    }
    return true;
  }

  public validatePayloadStructure(payload: any): boolean {
    // Check for required properties (adjust based on your token structure)
    const requiredProperties = ['sub', 'roles', 'exp'];
    for (const property of requiredProperties) {
      if (!payload.hasOwnProperty(property)) {
        return false;
      }
    }

    return true;
  }

  public isTokenExpired(payload: any): boolean {
    const expirationTime = payload.exp;
    return expirationTime < Date.now() / 1000;
  }


  public getUserRole(payload: any): string {
    return payload.roles[0].authority;
  }

  public getUserId(payload:any): string {
    return payload.sub;
  }

}
