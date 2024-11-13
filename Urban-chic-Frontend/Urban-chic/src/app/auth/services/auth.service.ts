import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { BehaviorSubject, catchError, map, Observable, of } from 'rxjs';
import { SellerRegistration } from '../models/seller-registration.model';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';
import { LoginResponse } from '../models/login-response.model';
import { SellerLoginRequest } from '../models/seller-login.model';
import { User } from '../models/user.model';
import { Buyer, BuyerLoginRequest, BuyerRegistrationRequest } from '../models/buyer.model';


@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {


  //url
  private readonly BASE_URL: string = 'http://localhost:8081/api/v1/auth';

  constructor(private http: HttpClient) { }


  //to create a user of seller type
  createSellerUser(formData: SellerRegistration): Observable<ApiResponse<User>> {
    const endpoint = `/register/seller`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<User>>(url, formData);
  }

  sellerLogin(formData: SellerLoginRequest): Observable<ApiResponse<LoginResponse>> {
    const endpoint = `/login/seller`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<LoginResponse>>(url, formData);
  }



  //buyer methods
  registerBuyer(formData:BuyerRegistrationRequest): Observable<ApiResponse<Buyer>> {
    const endpoint = `/register/buyer`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<Buyer>>(url, formData);
  }

  buyerLogin(formData:BuyerLoginRequest): Observable<ApiResponse<LoginResponse>>{
    const endpoint = `/login/buyer`;
    const url = `${this.BASE_URL}${endpoint} `;
    return this.http.post<ApiResponse<LoginResponse>>(url, formData);
  }
  

}