import { CommonModule } from '@angular/common';
import { Component,inject} from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { AuthServiceService } from '../../../../core/services/auth.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { SellerRegistration } from '../../../../core/models/auth-models/seller-registration.model';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { User } from '../../../../core/models/auth-models/user.model';
import { NotificationService } from '../../../../core/services/notification.service';
import { VerifyOtp } from '../../../../core/models/notification-model/verify-otp.model';
import { SmsOtpRequest } from '../../../../core/models/notification-model/sms-otp-request.model';
import { EmailOtpRequest } from '../../../../core/models/notification-model/email-otp-request.model';

@Component({
  selector: 'app-seller-registration',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './seller-registration.component.html',
  styleUrl: './seller-registration.component.css',
})
export class SellerRegistrationComponent {
  //angualar material 
  private snackBar = inject(MatSnackBar);



  //variable for elements design
  passwordBtnText: string = 'Show';
  showPassword: boolean = false;
  isOpen: boolean = false;
  isNumberVerified: boolean = false;
  isEmailVerified: boolean = false;
  otpModalContactInfo: string = '';
  otpModalContactType: string = '';
  passwordMatch: boolean = false;
  inpOtpValue: string = '';
  //other variables
  sellerRegistrationForm!: FormGroup;

  constructor(
    private authService: AuthServiceService,
    private notificationService: NotificationService,
    private fb: FormBuilder
  ) {
    //seller registration form builder
    this.sellerRegistrationForm = this.fb.group({
      sellerFullName: ['', Validators.required],
      sellerPrimaryMoNumber: [
        '',
        [
          Validators.required,
          Validators.minLength(10),
          Validators.pattern('^[0-9]+$'),
        ],
      ],
      sellerPrimaryEmail: ['', [Validators.required, Validators.email]],
      sellerPassword: [
        '',
        [
          Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20),
        ],
      ],
      confirmSellerPassword:[''],
    });
  }

  //---------------------- Non Api Method--------------------
  //method to open otp modal
  openModal(btn: string) {
    this.isOpen = true;
    if (btn == 'mobile') {
      this.otpModalContactType = 'number';
      this.otpModalContactInfo =
        this.sellerRegistrationForm.value.sellerPrimaryMoNumber;
      this.sendSmsOtp();
    } else if (btn == 'email') {
      this.otpModalContactType = 'email';
      this.otpModalContactInfo =
        this.sellerRegistrationForm.value.sellerPrimaryEmail;
      this.sendEmailOtp();
    }
  }
  //method to close otp modal
  closeModal() {
    this.isOpen = false;
  }

  //method to hide and show password
  togglePasswordVisibility(txt: string) {
    this.showPassword = !this.showPassword;
    if (txt == 'Show') {
      this.passwordBtnText = 'Hide';
    } else {
      this.passwordBtnText = 'Show';
    }
  }

  checkPasswordMatches(event: KeyboardEvent) {
    const input = event.target as HTMLInputElement;
    if (input.value.match(this.sellerRegistrationForm.value.sellerPassword)) {
      this.passwordMatch = true;
    } else {
      this.passwordMatch = false;
    }
  }


  //------------Api Calling Methods--------------
  sendSmsOtp() {
    const data: SmsOtpRequest = {
      userName: this.sellerRegistrationForm.value.sellerFullName,
      phoneNumber:
        '+91' + this.sellerRegistrationForm.value.sellerPrimaryMoNumber,
    };
    this.notificationService.sendSmsOtp(data).subscribe({
      next: (res: ApiResponse<string>) => {
        this.openSnackBar(res.data)
        console.log(res.data);
      },
      error: (err: any) => console.error(err),
    });
  }

  sendEmailOtp() {
    const data: EmailOtpRequest = {
      userName: this.sellerRegistrationForm.value.sellerFullName,
      email: this.sellerRegistrationForm.value.sellerPrimaryEmail,
    };
    this.notificationService.sendEmailOtp(data).subscribe({
      next: (res: ApiResponse<string>) => {
        console.log(res.data);
        this.openSnackBar(res.data)
      },
      error: (err: any) => console.error(err),
    });
  }

  verifyOtp() {
    let contactInfo = '';
    if(this.otpModalContactType === 'email') {
      contactInfo = this.otpModalContactInfo;
    }else{
      contactInfo = '+91' + this.otpModalContactInfo;
    }
    const data: VerifyOtp = {
      emailOrNumber: contactInfo,
      otpNumber: this.inpOtpValue,
    };
    console.log(data);

    this.notificationService.verifyOtp(data).subscribe({
      next: (res: ApiResponse<boolean>) => {
        this.openSnackBar(res.message)
        console.log(res);
        if (res.data && this.otpModalContactType == 'number') {
          console.log('Number is Verified');
          this.isNumberVerified = true;
          this.inpOtpValue = '';
          this.closeModal();
        } else if (res.data && this.otpModalContactType == 'email') {
          console.log('Email is Verified');
          this.isEmailVerified = true;
          this.inpOtpValue = '';
          this.closeModal();
        } else {
          this.inpOtpValue = '';
          console.log('Incoreect Otp');
        }
      },
      error: (err: any) => console.error(err),
    });
  }

   //to submit registration form
   submitSellerRegistrationForm() {
    const formData: SellerRegistration = {
      sellerFullName: this.sellerRegistrationForm.value.sellerFullName,
      sellerPrimaryMoNumber:
        this.sellerRegistrationForm.value.sellerPrimaryMoNumber,
      sellerPrimaryEmail: this.sellerRegistrationForm.value.sellerPrimaryEmail,
      sellerPassword: this.sellerRegistrationForm.value.sellerPassword,
    };
    console.log(formData);
    this.authService.createSellerUser(formData).subscribe({
      next: (res: ApiResponse<User>) => {
        this.openSnackBar(res.message);
        console.log(res);
        this.sellerRegistrationForm.reset();
      },
      error: (err: any) => console.error(err),
      complete: () => console.log('Registration completed'),
    });
  }

  openSnackBar(message: string){
    this.snackBar.open(message, 'Close', {
      duration: 3000,
    });
  }

}
