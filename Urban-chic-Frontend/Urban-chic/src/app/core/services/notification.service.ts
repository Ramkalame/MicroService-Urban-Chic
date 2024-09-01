import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SmsOtpRequest } from '../models/notification-model/sms-otp-request.model';
import { Observable } from 'rxjs';
import { ApiResponse } from '../models/shared-models/api-response.model';
import { VerifyOtp } from '../models/notification-model/verify-otp.model';
import { EmailOtpRequest } from '../models/notification-model/email-otp-request.model';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  private readonly BASE_URL:string='http://localhost:8081/api/v1/notifications';

  constructor(private http:HttpClient) { }

  sendSmsOtp(data:SmsOtpRequest):Observable<ApiResponse<string>>{
    const endpoint = `/otp/sms`;
    const url = `${this.BASE_URL}${endpoint}`
    return this.http.post<ApiResponse<string>>(url,data);
  }

  sendEmailOtp(data:EmailOtpRequest):Observable<ApiResponse<string>>{
    const endpoint = `/otp/email`;
    const url = `${this.BASE_URL}${endpoint}`
    return this.http.post<ApiResponse<string>>(url,data);
  }

  verifyOtp(data:VerifyOtp):Observable<ApiResponse<boolean>>{
    const endpoint = `/verify/otp/`;
    const url = `${this.BASE_URL}${endpoint}`
    return this.http.post<ApiResponse<boolean>>(url,data);
  }


}
