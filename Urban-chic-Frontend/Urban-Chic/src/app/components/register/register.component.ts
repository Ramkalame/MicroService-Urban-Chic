import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { OtpmodelComponent } from '../../otpmodel/otpmodel.component';
import { AuthService } from '../../service/login-registration/auth.service';
import { User } from '../../Model/user';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule, // CommonModule should also be imported in standalone components
    FormsModule,
    HttpClientModule,
    MatDialogModule // Import MatDialogModule as well
  ],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'] // Changed to styleUrls for correct usage
})
export class RegisterComponent {
  mobileNo: string = '';
  isVerified: boolean = false;

  constructor(public dialog: MatDialog, private authService: AuthService) { }

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

  sendOtpToNumber(){
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
