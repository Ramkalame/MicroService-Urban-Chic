import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProductRequest } from '../models/product-models/product-request.model';
import { Observable } from 'rxjs';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';
import { Product } from '../models/product-models/product-response.model';
import { Page } from '../../core/models/page-model.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly BASE_URL_PRODUCT_SELLERS:string='http://localhost:8081/api/v1/products/sellers';
  private readonly BASE_URL_PRODUCT:string='http://localhost:8081/api/v1/products';
  private readonly BASE_URL_PRODUCT_IMAGE:string='http://localhost:8081/api/v1/products/seller/image';


  constructor(private http:HttpClient) { }

  addProduct(product:ProductRequest):Observable<ApiResponse<Product>>{
    const endpoint = '/add';
    const url = `${this.BASE_URL_PRODUCT_SELLERS}${endpoint}`;
    return this.http.post<ApiResponse<Product>>(url,product);
  }

  uploadProductImage(productId:string,formData:FormData):Observable<ApiResponse<any>>{
    const endpoint = `/upload/${productId}`;
    const url = `${this.BASE_URL_PRODUCT_IMAGE}${endpoint}`;
    return this.http.post<ApiResponse<any>>(url,formData);
  }

  getAllProductsBySeller(sellerId:string,page:number=0,size:number=10): Observable<ApiResponse<Page<Product>>>{
    const endpoint = `/${sellerId}`;
    const params = new HttpParams().set('page',page).set('size',size);
    const url = `${this.BASE_URL_PRODUCT_SELLERS}${endpoint}`;
    return this.http.get<ApiResponse<Page<Product>>>(url,{params});
  }



}
