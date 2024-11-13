import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';
import { Address, Buyer, BuyerDetailsUpdateRequest } from '../../auth/models/buyer.model';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class BuyerService {

  //url
  private readonly BASE_URL: string = 'http://localhost:8081/api/v1/buyers';

  constructor(private http:HttpClient) { }

  fetchBuyerByPhoneNumber(buyerId: string):Observable<ApiResponse<Buyer>>{
    const endpoint = `/buyerId/${buyerId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.get<ApiResponse<Buyer>>(url);
  }

  updateBuyerName(name:string,buyerId:string):Observable<ApiResponse<Buyer>>{
    const endpoint = `/update-name/${name}/${buyerId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.put<ApiResponse<Buyer>>(url,name);
  }

  updateBuyerGender(gender:string,buyerId:string):Observable<ApiResponse<Buyer>>{
    const endpoint = `/update-gender/${gender}/${buyerId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.put<ApiResponse<Buyer>>(url,gender);
  }

  updateBuyerEmail(email:string, buyerId:string):Observable<ApiResponse<Buyer>>{
    const endpoint = `/update-email/${email}/${buyerId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.put<ApiResponse<Buyer>>(url,email);
  }

  updateBuyerPhoneNumber(phoneNumber:string, buyerId:string):Observable<ApiResponse<Buyer>>{
    const endpoint = `/update-phoneNumber/${phoneNumber}/${buyerId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.put<ApiResponse<Buyer>>(url,phoneNumber);
  }

  saveAddress(formData:Address,buyerId:string){
    const endpoint = `/${buyerId}/address`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.post<ApiResponse<Address>>(url,formData);
  }

  getAllAddresses(buyerId:string){
    const endpoint = `/${buyerId}/addresses`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.get<ApiResponse<Address[]>>(url);
  }

  deleteAddressById(addressId:string,buyerId:string){
    const endpoint = `/${buyerId}/address/${addressId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.delete<ApiResponse<any>>(url);
  }

  updateAddressById(formData:Address,addressId:string,buyerId:string){
    const endpoint = `/${buyerId}/address/${addressId}`;
    const url = `${this.BASE_URL}${endpoint}`;
    return this.http.patch<ApiResponse<Address[]>>(url,formData);
  }

  


}
