import { Injectable } from '@angular/core';
import { SellerDocumentRequest } from '../models/seller-documents-request.model';
import { Observable } from 'rxjs';
import { SellerDocument } from '../models/seller-document.model';
import { HttpClient } from '@angular/common/http';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class SellerService {

  private readonly BASE_URL_DOCUMENTS:string='http://localhost:8081/api/v1/sellers/documents';
  private readonly BASE_URL_IMAGES:string='http://localhost:8081/api/v1/sellers/images';

  constructor(private http:HttpClient) { }


  uploadImage(file:File): Observable<any>{
    const formData = new FormData();
    formData.append('file', file);
    return this.http.post(`${this.BASE_URL_IMAGES}/upload`, formData);
  }

  addSellerDocuments(formData:SellerDocumentRequest):Observable<ApiResponse<SellerDocument>>{
    const endpoint = `/add`;
    const url = `${this.BASE_URL_DOCUMENTS}${endpoint}`;
    return this.http.post<ApiResponse<SellerDocument>>(url,formData);
  }
  
}
