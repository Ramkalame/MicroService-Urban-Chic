import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/shared-models/api-response.model';
import { User } from '../models/auth-models/user.model';
import { SellerRegistration } from '../models/auth-models/seller-registration.model';
import { SellerLoginRequest } from '../models/auth-models/seller-login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  private readonly BASE_URL:string='http://localhost:8081/api/v1/auth';

  constructor(private http:HttpClient) { }

  //to create a user of seller type
  createSellerUser(formData:SellerRegistration):Observable<ApiResponse<User>>{
    const endpoint = `/register/seller`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<User>>(url,formData);
  }

  sellerLogin(formData:SellerLoginRequest):Observable<ApiResponse<string>>{
    const endpoint = `/login/seller`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<string>>(url,formData);
  }

}
