import { inject, Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { UserRole } from '../enums/user-roles';
import { JwtDecoderService } from './jwt-decoder.service';

@Injectable({
  providedIn: 'root'
})
export class SellerAuthService {

  localStorage = inject(LocalStorageService);
  jwtDecoder = inject(JwtDecoderService);

  constructor() { }

  login(token: string) {
    this.localStorage.setToken(token);
  }

  logout() {
    this.localStorage.removeToken();
  }

  getUserId(){
    return this.jwtDecoder.getUser();
  }

  isLoggedIn(): boolean {
    const token = this.localStorage.getToken();
    if(!token){
      return false;
    }else if(this.jwtDecoder.isTokenExpired()){
      this.localStorage.removeToken();
      return false;
    }else{
      return true;
    }
  }

  isAuthorized(): boolean {
      return this.jwtDecoder.getRole() === UserRole.ROLE_SELLER;
  }


}
