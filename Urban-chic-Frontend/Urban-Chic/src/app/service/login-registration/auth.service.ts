import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../../Model/user';
import { environment } from '../../../environment/environment';
import { AppConstants } from '../../constants/app.constant';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private http: HttpClient) { }

  signUp(user: User): Observable<any> {
    console.log(user);
    return this.http.post(environment.baseUrl + AppConstants.REGISTER_API_URL, user);
    // return this.http.post('http://192.168.1.34:8083/auth/register', user);
    // return this.http.post('http://192.168.1.34:8082/users/create', user);
  }

  verifyOtp(mobileNumber: string, otp: string): Observable<any> {
    const body = { mobileNumber, otp };
    return this.http.post(environment.baseUrl + AppConstants.VERIFY_OTP_URL, body);
  }

  sendOtp(mobileNumber: string): Observable<any> {
    const body = { mobileNumber };
    return this.http.post(environment.baseUrl + AppConstants.SEND_OTP_URL, body);
  }

  googleSignUp(user: any){
    return this.http.post(environment.baseUrl + AppConstants.GOOGLE_SIGNIN_API_URL, user);
  }
  
}
