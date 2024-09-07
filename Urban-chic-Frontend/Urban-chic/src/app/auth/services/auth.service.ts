import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { catchError, map, Observable, of } from 'rxjs';
import { SellerRegistration } from '../models/seller-registration.model';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';
import { LoginResponse } from '../models/login-response.model';
import { SellerLoginRequest } from '../models/seller-login.model';
import { User } from '../models/user.model';
import { JwtDecoderService } from '../../core/services/jwt-decoder.service';
import { SnackbarService } from '../../common/services/snackbar.service';
import { UserRole } from '../../core/enums/user-roles';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {

  jwt = inject(JwtDecoderService);
  snackBar = inject(SnackbarService)
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


  isLoggedIn(): Observable<boolean> {
    const storedToken = localStorage.getItem('token');

    if (!storedToken) {
      this.showError(401, 'Unauthorized', 'Token Not Found');
      return of(false);
    }

    return this.jwt.decodeToken(storedToken).pipe(
      map((decodedToken: any) => {
        if (!decodedToken) {
          this.showError(401, 'Unauthorized', 'Invalid token!! Please Login');
          localStorage.removeItem('token');
          return false;
        }

        if (!this.jwt.validatePayloadStructure(decodedToken)) {
          this.showError(401, 'Unauthorized', 'Invalid token structure!! Please Login');
          localStorage.removeItem('token');
          return false;
        }

        if (this.jwt.isTokenExpired(decodedToken)) {
          this.showError(401, 'Unauthorized', 'Expired token!! Please Login');
          localStorage.removeItem('token');
          return false;
        }

        const userRole = this.jwt.getUserRole(decodedToken);
        if (userRole === UserRole.ROLE_SELLER) {
          return true;
        } else {
          this.showError(403, 'Forbidden', 'Access denied');
          localStorage.removeItem('token');
          return false;
        }
      }),
      catchError(() => {
        this.showError(401, 'Unauthorized', 'Error decoding token');
        localStorage.removeItem('token');
        return of(false);
      })
    );
  }

  getUserId(): string{
    const storedToken = localStorage.getItem('token');
    if (!storedToken) {
      return ""; // Token not found
    }
    try {
      const decodedToken: any = this.jwt.decodeToken(storedToken);
      if (!decodedToken || !decodedToken.sub) {
        return ""; // User ID not found in token
      }
      return decodedToken.sub; // Assuming `sub` is the user ID claim
    } catch (error) {
      console.error('Error decoding token:', error);
      return ""; // Error occurred during token decoding
    }
  }


  // Helper function for showing errors
  private showError(status: number, statusText: string, message: string): void {
    const customError = new HttpErrorResponse({
      status: status,
      statusText: statusText,
      error: { message: message }
    });

    this.snackBar.openFailedSnackBar(customError);
  }


}


