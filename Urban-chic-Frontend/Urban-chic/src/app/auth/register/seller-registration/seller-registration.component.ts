import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, inject, signal } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthServiceService } from '../../../../core/services/auth.service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators, } from '@angular/forms';
import {MatDialog,MAT_DIALOG_DATA,MatDialogTitle,MatDialogContent, MatDialogModule, MatDialogRef,} from '@angular/material/dialog';
import {MatButtonModule} from '@angular/material/button';
import { SellerRegistration } from '../../../../core/models/auth-models/seller-registration.model';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { User } from '../../../../core/models/auth-models/user.model';
import { NotificationService } from '../../../../core/services/notification.service';
import { VerifyOtp } from '../../../../core/models/notification-model/verify-otp.model';
import { SmsOtpRequest } from '../../../../core/models/notification-model/sms-otp-request.model';
import { EmailOtpRequest } from '../../../../core/models/notification-model/email-otp-request.model';
import { MatFormField, MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { merge } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

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
    MatButtonModule,MatDialogTitle,MatDialogContent,MatDialogModule],
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

  //error siganals
  fullNameError = signal('');
  mobileNumberError = signal('');
  emailError = signal('');
  passwordError = signal('');
  confirmPasswordError = signal('');

  //other properties
  isNumberVerified:boolean =false;
  isEmailVerified:boolean = false;
  isDialogOpen:boolean = false;


  private snackBar = inject(MatSnackBar);
  private dialog = inject(MatDialog);
  hide = signal(true);

  constructor() {
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
      this.confirmPassword.valueChanges
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
  }

  //to show and hide password 
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }

  //To open snack bar
  openSnackBar(message: string){
    this.snackBar.open(message, 'Close', {
      duration: 3000,
    });
  }

  openOtpDialog(type:string){
    this.isDialogOpen = true
  }

  closeOtpDialog(){
    this.isDialogOpen = false;
  }
 


}
