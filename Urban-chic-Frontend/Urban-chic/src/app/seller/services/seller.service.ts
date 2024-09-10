import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SellerDocument } from '../models/response-models/seller-document.model';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';
import { SellerDocumentRequest } from '../models/request-models/seller-documents-request.model';
import { Seller } from '../models/response-models/seller.model';
import { SellerRequest } from '../models/request-models/seller-request.model';
import { UpdateSellerRequest } from '../models/request-models/update-seller-document.model';
import { SellerAddress } from '../models/response-models/seller-address.model';
import { SellerAddressRequest } from '../models/request-models/seller-address-reqest.model';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  private readonly BASE_URL_SELLERS:string='http://localhost:8081/api/v1/sellers';
  private readonly BASE_URL_DOCUMENTS:string='http://localhost:8081/api/v1/sellers/documents';
  private readonly BASE_URL_ADDRESS:string='http://localhost:8081/api/v1/sellers/address';
  private readonly BASE_URL_IMAGES:string='http://localhost:8081/api/v1/sellers/images';

  constructor(private http:HttpClient) { }

// company logo api methods
  uploadImage(file:File): Observable<any>{
    const formData = new FormData();
    formData.append('file', file);
    const endpoint = '/upload';
    const url = `${this.BASE_URL_IMAGES}${endpoint}`;
    return this.http.post(url, formData);
  }


  updateImage(image:File,sellerId:string): Observable<any>{
    const formData = new FormData();
    formData.append('file', image);
    const endpoint = `/update/${sellerId}`;
    const url = `${this.BASE_URL_IMAGES}${endpoint}`;
    return this.http.post(url, formData);
  }

  //seller documents api methods
  addSellerDocuments(formData:SellerDocumentRequest):Observable<ApiResponse<SellerDocument>>{
    const endpoint = `/add`;
    const url = `${this.BASE_URL_DOCUMENTS}${endpoint}`;
    return this.http.post<ApiResponse<SellerDocument>>(url,formData);
  }

  //seller details api methods
  getSellersDetails(sellerId: string): Observable<ApiResponse<Seller>>{
    const endpoint = `/${sellerId}`;
    const url = `${this.BASE_URL_SELLERS}${endpoint}`;
    return this.http.get<ApiResponse<Seller>>(url);
  }

  updateSellersDetails(sellerId: string,formData:SellerRequest): Observable<ApiResponse<Seller>>{
    const endpoint = `/update/${sellerId}`;
    const url = `${this.BASE_URL_SELLERS}${endpoint}`;
    return this.http.put<ApiResponse<Seller>>(url,formData);
  }

  //seller documents api methods
  getSellerDocuments(sellerId:string): Observable<ApiResponse<SellerDocument>>{
    const endpoint = `/seller/${sellerId}`;
    const url = `${this.BASE_URL_DOCUMENTS}${endpoint}`;
    return this.http.get<ApiResponse<SellerDocument>>(url);
  }

  updateSellerDocuments(sellerId:string, formData:UpdateSellerRequest): Observable<ApiResponse<SellerDocument>>{
    const endpoint = `/update/${sellerId}`;
    const url = `${this.BASE_URL_DOCUMENTS}${endpoint}`;
    return this.http.put<ApiResponse<SellerDocument>>(url,formData);
  }

  //seller address api methods
  getSellersAddress(sellerId: string): Observable<ApiResponse<SellerAddress>>{
    const endpoint = `/seller/${sellerId}`;
    const url = `${this.BASE_URL_ADDRESS}${endpoint}`;
    return this.http.get<ApiResponse<SellerAddress>>(url);
  }

  updateSellersAddress(sellerId: string, formData: SellerAddressRequest): Observable<ApiResponse<SellerAddress>>{
    const endpoint = `/update/${sellerId}`;
    const url = `${this.BASE_URL_ADDRESS}${endpoint}`;
    return this.http.put<ApiResponse<SellerAddress>>(url,formData);
  }

  


  
}
