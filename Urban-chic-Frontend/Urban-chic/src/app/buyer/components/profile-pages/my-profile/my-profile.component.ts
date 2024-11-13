import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  Component,
  inject,
  OnInit,
  TemplateRef,
  ViewChild,
} from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { SnackbarService } from '../../../../common/services/snackbar.service';
import { NotificationService } from '../../../../core/services/notification.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import {
  Buyer,
  BuyerDetailsUpdateRequest,
} from '../../../../auth/models/buyer.model';
import { BuyerService } from '../../../services/buyer.service';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { JwtDecoderService } from '../../../../core/services/jwt-decoder.service';
import { Gender } from '../../../../core/enums/gender';
import { BuyerAuthService } from '../../../../core/services/buyer-auth.service';

@Component({
  selector: 'app-my-profile',
  standalone: true,
  imports: [
    CommonModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatRadioModule,
    MatDialogModule,
    ReactiveFormsModule,
  ],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css',
})
export class MyProfileComponent implements AfterViewInit, OnInit {
  @ViewChild('otpEmailTemplate') otpTemplate!: TemplateRef<any>;
  otpDialogRef!: MatDialogRef<any>;

  disableFullName: boolean = true;
  disableGender: boolean = true;
  disableEmail: boolean = true;
  disableMobileNumber: boolean = true;

  gender = Gender;
  contactType: string = '';
  //other properties

  myForm!: FormGroup;
  buyerDetails!: Buyer;

  //service injection
  snackbar = inject(SnackbarService);
  notificationService = inject(NotificationService);
  router = inject(Router);
  buyerService = inject(BuyerService);
  buyerAuthService = inject(BuyerAuthService);

  constructor(private dialog: MatDialog, private fb: FormBuilder) {
    this.myForm = this.fb.group({
      otp: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9]*$'),
        Validators.minLength(4),
        Validators.maxLength(4),
      ]),
      fullName: new FormControl(
        { value: '', disabled: this.disableFullName },
        Validators.required
      ),
      gender: new FormControl(
        { value: '', disabled: this.disableGender },
        Validators.required
      ),
      email: new FormControl({ value: '', disabled: this.disableEmail }, [
        Validators.required,
        Validators.email,
      ]),
      mobileNumber: new FormControl(
        { value: '', disabled: this.disableMobileNumber },
        [
          Validators.required,
          Validators.pattern('^[0-9]*$'),
          Validators.minLength(10),
          Validators.maxLength(10),
        ]
      ),
    });
  }
  ngOnInit(): void {
    this.fetchBuyerDetails();
  }
  ngAfterViewInit(): void {
    //console.log(this.otpTemplate);
  }

  enableFullName() {
    this.disableFullName = !this.disableFullName;
    this.myForm.get('fullName')?.enable();
  }

  cancelFullName() {
    this.disableFullName = true;
    this.myForm.get('fullName')?.disable();
    this.myForm.patchValue({
      fullName: this.buyerDetails.name ?? 'Enter your name',
    });
  }
  enableGender() {
    this.disableGender = !this.disableGender;
    this.myForm.get('gender')?.enable();
  }

  cancelGender() {
    this.disableGender = true;
    this.myForm.get('gender')?.disable();
  }

  enableEmail() {
    this.disableEmail = !this.disableEmail;
    this.myForm.get('email')?.enable();
  }
  cancelEmail() {
    this.disableEmail = true;
    this.myForm.get('email')?.disable();
    this.myForm.patchValue({
      email: this.buyerDetails.email ?? 'Enter your email',
    });
  }

  enableMobileNumber() {
    this.disableMobileNumber = !this.disableMobileNumber;
    this.myForm.get('mobileNumber')?.enable();
  }
  cancelMobileNumber() {
    this.disableMobileNumber = true;
    this.myForm.get('mobileNumber')?.disable();
  }

  openOtpDialog(): void {
    this.otpDialogRef = this.dialog.open(this.otpTemplate, {
      disableClose: true,
      width: '400px',
    });
  }

  closeOtpDialog(): void {
    this.otpDialogRef.close();
  }

  saveEmail() {
    this.contactType = 'email';
    this.disableEmail = true;
    this.openOtpDialog();
    this.snackbar.openSuccessSnackBar('Otp Sent to email ');
  }

  saveMobileNumber() {
    this.contactType = 'mobileNumber';
    this.disableMobileNumber = true;
    this.openOtpDialog();
    this.snackbar.openSuccessSnackBar('Otp Sent to number ');
  }

  sendOtp() {
    // console.log(this.otp.value);
  }

  submitOtp() {
    console.log(this.myForm.value.otp);
  }

  buyerLogout() {
    this.buyerAuthService.logout();
    this.router.navigate(['/auth/login']);
    this.snackbar.openSuccessSnackBar('Logged out successfully');
  }

  fetchBuyerDetails() {
    const buyerId = this.buyerAuthService.getUserId();
    console.log(buyerId);
    this.buyerService.fetchBuyerByPhoneNumber(buyerId).subscribe({
      next: (res: ApiResponse<Buyer>) => {
        this.buyerDetails = res.data;
        console.log(this.buyerDetails);
        this.myForm.patchValue({
          fullName: this.buyerDetails.name ?? 'Enter Your Name',
          gender: this.buyerDetails.gender ?? '',
          email: this.buyerDetails.email ?? 'Enter your email',
          mobileNumber: this.buyerDetails.phoneNumber,
        });
      },
      error: (error: any) => {
        console.error(error);
        this.snackbar.openStringMsgFailedSnackBar('Something went wrong!');
      },
    });
  }

  updateBuyerName() {
    const name: string = this.myForm.value.fullName;
    console.log(name);
    const buyerId = this.buyerAuthService.getUserId();
    this.buyerService.updateBuyerName(name, buyerId).subscribe({
      next: (res: ApiResponse<Buyer>) => {
        this.buyerDetails = res.data;
        this.myForm.patchValue({ fullName: this.buyerDetails.name });
        this.snackbar.openSuccessSnackBar('Name updated successfully');
        this.cancelFullName();
      },
      error: (error: any) => {
        console.error(error);
        this.snackbar.openStringMsgFailedSnackBar('Something went wrong!');
      },
    });
  }

  updateBuyerEmail() {
    const email: string = this.myForm.value.email;
    console.log(email);
    const buyerId = this.buyerAuthService.getUserId();
    this.buyerService.updateBuyerEmail(email, buyerId).subscribe({
      next: (res: ApiResponse<Buyer>) => {
        this.buyerDetails = res.data;
        this.myForm.patchValue({ email: this.buyerDetails.email });
        this.snackbar.openSuccessSnackBar('Email updated successfully');
        this.cancelEmail();
      },
      error: (error: any) => {
        console.error(error);
        this.snackbar.openStringMsgFailedSnackBar('Something went wrong!');
      },
    });
  }

  updateBuyerGender() {
    const gender: string = this.myForm.get('gender')?.value;
    console.log('Selected Gender value: ' + gender);
    const buyerId = this.buyerAuthService.getUserId();
    this.buyerService.updateBuyerGender(gender, buyerId).subscribe({
      next: (res: ApiResponse<Buyer>) => {
        this.buyerDetails = res.data;
        this.myForm.patchValue({ gender: this.buyerDetails.gender });
        this.snackbar.openSuccessSnackBar('Gender updated successfully');
        this.cancelGender();
      },
      error: (error: any) => {
        console.error(error);
        this.snackbar.openStringMsgFailedSnackBar('Something went wrong!');
      },
    });
  }
}
