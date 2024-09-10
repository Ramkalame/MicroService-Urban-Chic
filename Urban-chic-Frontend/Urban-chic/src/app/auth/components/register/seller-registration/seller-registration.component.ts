import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, signal } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators, } from '@angular/forms';
import { MatDialog, MAT_DIALOG_DATA, MatDialogTitle, MatDialogContent, MatDialogModule, MatDialogRef, } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { merge } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { Router } from '@angular/router';
import { HttpErrorResponse } from '@angular/common/http';
import { SnackbarService } from '../../../../common/services/snackbar.service';
import { NotificationService } from '../../../../core/services/notification.service';
import { AuthServiceService } from '../../../services/auth.service';
import { SmsOtpRequest } from '../../../../core/models/notification-model/sms-otp-request.model';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { EmailOtpRequest } from '../../../../core/models/notification-model/email-otp-request.model';
import { VerifyOtp } from '../../../../core/models/notification-model/verify-otp.model';
import { SellerRegistration } from '../../../models/seller-registration.model';
import { User } from '../../../models/user.model';

@Component({
  selector: 'app-seller-registration',
  standalone: true,
  imports: [CommonModule,
    ReactiveFormsModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatLabel,
    MatButtonModule, MatDialogTitle, MatDialogContent, MatDialogModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './seller-registration.component.html',
  styleUrl: './seller-registration.component.css',
})
export class SellerRegistrationComponent {

  //form data
  readonly fullName = new FormControl('', Validators.required);
  readonly mobileNumber = new FormControl('', [Validators.required, Validators.maxLength(10), Validators.minLength(10)])
  readonly email = new FormControl('', [Validators.required, Validators.email])
  readonly password = new FormControl('', [Validators.required, Validators.minLength(6)])
  readonly confirmPassword = new FormControl('', [Validators.required])
  readonly otp = new FormControl('', [Validators.required, Validators.minLength(4)])

  //error siganals
  fullNameError = signal('');
  mobileNumberError = signal('');
  emailError = signal('');
  passwordError = signal('');
  confirmPasswordError = signal('');
  otpError = signal('');

  //other properties
  isNumberVerified: boolean = true;
  isEmailVerified: boolean = false;
  isDialogOpen: boolean = false;
  otpContactType: string = '';
  otpDialogText: string = '';

  private snackbar = inject(SnackbarService);
  hide = signal(true);

  constructor(private authService: AuthServiceService,
    private notificationService: NotificationService,
    private router: Router,
    private cdr: ChangeDetectorRef) {
    merge(
      this.fullName.statusChanges,
      this.fullName.valueChanges,
      this.mobileNumber.statusChanges,
      this.mobileNumber.valueChanges,
      this.email.statusChanges,
      this.email.valueChanges,
      this.password.statusChanges,
      this.password.valueChanges,
      this.confirmPassword.statusChanges,
      this.confirmPassword.valueChanges,
      this.otp.statusChanges,
      this.otp.valueChanges
    )
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage())
  }

  checkPasswordMatch() {
    if (this.password.value !== this.confirmPassword.value) {
      this.confirmPasswordError.set('Passwords do not match');
    } else {
      this.confirmPasswordError.set('');
    }
  }

  updateErrorMessage() {
    // Full Name Error
    if (this.fullName.hasError('required')) {
      this.fullNameError.set('Full name is required');
    } else {
      this.fullNameError.set('');
    }

    // Mobile Number Error
    if (this.mobileNumber.hasError('required')) {
      this.mobileNumberError.set('Mobile number is required');
    } else if (this.mobileNumber.hasError('minlength') || this.mobileNumber.hasError('maxlength')) {
      this.mobileNumberError.set('Mobile number must be exactly 10 digits');
    } else {
      this.mobileNumberError.set('');
    }

    // Email Error
    if (this.email.hasError('required')) {
      this.emailError.set('You must enter a value');
    } else if (this.email.hasError('email')) {
      this.emailError.set('Not a valid email');
    } else {
      this.emailError.set('');
    }

    // Password Error
    if (this.password.hasError('required')) {
      this.passwordError.set('Password is required');
    } else if (this.password.hasError('minlength')) {
      this.passwordError.set('Password must be at least 6 characters long');
    } else {
      this.passwordError.set('');
    }

    // Confirm Password Error
    if (this.confirmPassword.hasError('required')) {
      this.confirmPasswordError.set('You must confirm your password');
    } else {
      this.confirmPasswordError.set('');
    }

    // OTP Error
    if (this.otp.hasError('required')) {
      this.otpError.set('OTP is required');
    } else if (this.otp.hasError('minlength')) {
      this.otpError.set('OTP must be exactly 4 digits');
    } else {
      this.otpError.set('');
    }
  }

  //to show and hide password 
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }


  openOtpDialog() {
    this.isDialogOpen = true
  }

  closeOtpDialog() {
    this.isDialogOpen = false;
  }

  //Api Methods
  sendSmsOtp(type: string) {
    this.otpContactType = type;
    this.otpDialogText = type + ' ' + 'number +91-' + this.mobileNumber.value;
    this.openOtpDialog()
    const data: SmsOtpRequest = {
      userName: this.fullName.value!,
      phoneNumber: '+91' + this.mobileNumber.value!
    };
    console.log(data);
    this.notificationService.sendSmsOtp(data).subscribe({
      next: (res: ApiResponse<string>) => {
        this.snackbar.openSuccessSnackBar(res.message);
      },
      error: (error:HttpErrorResponse) => {
        console.error(error)
        this.snackbar.openFailedSnackBar(error);
      },
    });
  }

  sendEmailOtp(type: string) {
    this.otpContactType = type;
    this.otpDialogText = type + ' ' + this.email.value;
    this.openOtpDialog()
    const data: EmailOtpRequest = {
      userName: this.fullName.value!,
      email: this.email.value!
    }
    console.log(data);
    this.notificationService.sendEmailOtp(data).subscribe({
      next: (res: ApiResponse<string>) => {
        this.snackbar.openSuccessSnackBar(res.message);
      },
      error: (error: HttpErrorResponse) => {
        console.error(error)
        this.snackbar.openFailedSnackBar(error);
      },
    });
  }

  verifyOtp() {
    let data: VerifyOtp;
    if (this.otpContactType === 'mobile') {
      data = {
        emailOrNumber: '+91' + this.mobileNumber.value!,
        otpNumber: this.otp.value!,
      }
    }
    else {
      data = {
        emailOrNumber: this.email.value!,
        otpNumber: this.otp.value!,
      }
    }
    console.log(data);

    this.notificationService.verifyOtp(data).subscribe({
      next: (res: ApiResponse<boolean>) => {
        if (res.data) {
          if (this.otpContactType === 'mobile') {
            console.log('inside mobile ');
            this.isNumberVerified = true;
          }
          else {
            console.log('inside email ');
            this.isEmailVerified = true;
          }
          this.isDialogOpen = false;
          this.cdr.detectChanges();
          this.snackbar.openSuccessSnackBar(res.message);
        }
        else {
          this.snackbar.openSuccessSnackBar(res.message);
        }
        this.otp.reset();
      },
      error: (error: HttpErrorResponse) => {
        console.error(error)
        this.snackbar.openFailedSnackBar(error);
      },
    });
  }

  resendOtp() {
    if (this.otpContactType === 'mobile') {
      const data: SmsOtpRequest = {
        userName: this.fullName.value!,
        phoneNumber: '+91' + this.mobileNumber.value!
      };
      console.log(data);
      this.notificationService.sendSmsOtp(data).subscribe({
        next: (res: ApiResponse<string>) => {
          this.snackbar.openSuccessSnackBar(res.message);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error)
          this.snackbar.openFailedSnackBar(error);
        },
      });
    } else {
      this.openOtpDialog()
      const data: EmailOtpRequest = {
        userName: this.fullName.value!,
        email: this.email.value!
      }
      console.log(data);
      this.notificationService.sendEmailOtp(data).subscribe({
        next: (res: ApiResponse<string>) => {
          this.snackbar.openSuccessSnackBar(res.message);
        },
        error: (error: HttpErrorResponse) => {
          console.error(error)
          this.snackbar.openFailedSnackBar(error);
        },
      });
    }
  }


  submitSellerRegistration() {
    const data: SellerRegistration = {
      sellerFullName: this.fullName.value!,
      sellerPrimaryMoNumber: '+91' + this.mobileNumber.value!,
      sellerPrimaryEmail: this.email.value!,
      sellerPassword: this.password.value!
    }
    console.log(data);
    this.authService.createSellerUser(data).subscribe({
      next: (res: ApiResponse<User>) => {
        if (res.success) {
          this.snackbar.openSuccessSnackBar(res.message);
          this.router.navigate(['/auth/login/seller'])
          this.resetForm();
        } else {
          this.snackbar.openSuccessSnackBar(res.message);
        }
      },
      error: (error: HttpErrorResponse) => {
        console.error(error)
        this.snackbar.openFailedSnackBar(error);
      },
    })

  }

  resetForm() {
    // Reset form controls
    this.fullName.reset();
    this.mobileNumber.reset();
    this.email.reset();
    this.password.reset();
    this.confirmPassword.reset();
    this.otp.reset();
  }


}
