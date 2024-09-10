import { inject, Injectable } from '@angular/core';
import { SnackbarService } from '../../common/services/snackbar.service';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';
import { catchError, Observable, of } from 'rxjs';
import { UserRole } from '../enums/user-roles';
import  {jwtDecode, JwtPayload} from 'jwt-decode'
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root'
})
export class JwtDecoderService {

  localStorage = inject(LocalStorageService);

  jwtToken!: string;
  // decodedToken!: { [key: string]: string };
  decodedTokenPayload: any;

  constructor() { }

  private setToken() {
    const token = this.localStorage.getToken();
    if(token){
      this.jwtToken = token;
    }
  }

 private decodeToken(){
    this.setToken();
    if(this.jwtToken){
      this.decodedTokenPayload= jwtDecode(this.jwtToken);
    }
  }

  getUser():string{
    this.decodeToken();
    return this.decodedTokenPayload ? this.decodedTokenPayload.sub: null;
  }

 private getExpiryTime(){
    this.decodeToken();
    return this.decodedTokenPayload? this.decodedTokenPayload.exp: null;
  }

  getRole(){
    this.decodeToken();
    return this.decodedTokenPayload? this.decodedTokenPayload.roles[0].authority: null;
  }

  isTokenExpired(): boolean {
    const expiryTime = this.getExpiryTime(); // can be number, null, or undefined
    if (expiryTime !== null && expiryTime !== undefined) {
      return ((1000 * expiryTime) - (new Date()).getTime()) < 50000;
    } else {
      return false; // Token is not expired or there's no token
    }
  }
  

  
}


