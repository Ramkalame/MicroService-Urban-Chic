declare var google: any;

import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { OtpmodelComponent } from '../../otpmodel/otpmodel.component';
import { AuthService } from '../../service/login-registration/auth.service';
import { User } from '../../Model/user';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule,
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  mobileNo: string = '';
  isVerified: boolean = false;

  constructor(public dialog: MatDialog, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    google.accounts.id.initialize({
      client_id: '267707298863-36om52pa0mu9j7i2h8qa5cvln1o42pv1.apps.googleusercontent.com',
      callback: (response: any) => {
        // console.log("Google Response: ", response);
        this.handleGoogleResponse(response);
      }
    });
    google.accounts.id.renderButton(document.getElementById("google-button"), {
      theme: "outline",
      size: "large",
      shape: "rectangle",
      logo_alignment: "left",
      width: 600
    });
  }

  decodeToken(token: string): any {
    const base64Url = token.split('.')[1];
    const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    const jsonPayload = decodeURIComponent(
      atob(base64)
      .split('')
      .map(function (c) {
      return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));
    return JSON.parse(jsonPayload);
  }

  handleGoogleResponse(response: any) {
    if (response) {
      // Decode the JWT token
      const jsonPayload = this.decodeToken(response.credential);
      // console.log("Google Token Payload: ", jsonPayload);

      // Store the user details in session storage
      // sessionStorage.setItem('loggedInUser', JSON.stringify(jsonPayload));

      // Post the details to the backend
      const user = {
        fullName: jsonPayload.name,
        email: jsonPayload.email,
        mobileNo: "7879094469",
        profileImageUrl: jsonPayload.picture
      };
      this.authService.googleSignUp(user).subscribe({
        next: response => {
          console.log("Google Signup Successful: ", response);
          this.router.navigate(['/']);
        },
        error: error => {
          console.log("Google Signup Failed: ", error);
        }
      });
    }
  }

  openVerifyPopup(): void {
    const dialogRef = this.dialog.open(OtpmodelComponent, {
      width: '250px',
      data: { mobileNo: this.mobileNo }
    });
    this.sendOtpToNumber();

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.verifyOtp(result);
      }
    });
  }

  sendOtpToNumber() {
    this.authService.sendOtp(this.mobileNo).subscribe({
      next: response => {
        console.log('OTP sent Successfully: ', response);
      },
      error: error => {
        console.log('Error while sending OTP: ', error);
      }
    });
  }

  verifyOtp(otp: string): void {
    this.authService.verifyOtp(this.mobileNo, otp).subscribe({
      next: response => {
        console.log('OTP Verified Successfully: ', response);
      },
      error: error => {
        console.log('Error while verifying OTP: ', error);
      }
    });
  }

  onSubmit(form: NgForm): void {
    if (!this.isVerified) {
      const user: User = new User(
        form.value.fullName,
        form.value.email,
        form.value.mobileNo
      );

      this.authService.signUp(user).subscribe({
        next: response => {
          console.log("Registration Successful: ", response);
        },
        error: error => {
          console.error("Registration Failed: ", error);
        }
      });
    } else {
      console.log('Please verify your mobile number first !');
    }
  }
}
