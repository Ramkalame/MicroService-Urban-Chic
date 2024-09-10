import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LocalStorageService {

  constructor() { }

  setToken(value: string){
      localStorage.setItem('token', value);
  }

  getToken(){
    return localStorage.getItem('token');
  }
  
  removeToken(){
    if (localStorage.getItem('token')) {
      localStorage.removeItem('token');
    }
  }

}
