import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import {MatRadioModule  } from '@angular/material/radio';
import { SnackbarService } from '../../../../common/services/snackbar.service';
import { NotificationService } from '../../../../core/services/notification.service';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-my-profile',
  standalone: true,
  imports: [CommonModule,MatFormFieldModule,MatInputModule,MatButtonModule,MatIconModule,MatRadioModule,MatDialogModule,ReactiveFormsModule],
  templateUrl: './my-profile.component.html',
  styleUrl: './my-profile.component.css'
})
export class MyProfileComponent implements AfterViewInit {

  @ViewChild('otpEmailTemplate') otpTemplate!:TemplateRef<any>;
  otpDialogRef!: MatDialogRef<any>;

  disableFullName: boolean = true;
  disableGender: boolean = true;
  disableEmail: boolean = true;
  disableMobileNumber: boolean = true;

  contactType: string = '';
  //other properties
  
  myForm!:FormGroup;

  //service injection
  snackbar = inject(SnackbarService);
  notificationService = inject(NotificationService);

  constructor(private dialog:MatDialog,private fb:FormBuilder){
    this.myForm = this.fb.group({
      otp:new FormControl('',[Validators.required,Validators.pattern('^[0-9]*$'),Validators.minLength(4),Validators.maxLength(4)]),
      fullName: new FormControl('',Validators.required),
      gender: new FormControl('',Validators.required),
      email: new FormControl('',[Validators.required,Validators.email]),
      mobileNumber: new FormControl('',[Validators.required,Validators.pattern('^[0-9]*$'),Validators.minLength(10),Validators.maxLength(10)]),
    });
  }
  ngAfterViewInit(): void {
    console.log(this.otpTemplate);
    
  }

  enableFullName(){
    this.disableFullName = !this.disableFullName;
  }

  cancelFullName(){
    this.disableFullName = true;
  }
  enableGender(){
    this.disableGender = !this.disableGender;
  }

  cancelGender(){
    this.disableGender = true;
  }

  enableEmail(){
    this.disableEmail =!this.disableEmail;
  }
  cancelEmail(){
    this.disableEmail = true;
  }

  enableMobileNumber(){
    this.disableMobileNumber =!this.disableMobileNumber;
  }
  cancelMobileNumber(){
    this.disableMobileNumber = true;
  }

  openOtpDialog(): void {
    this.otpDialogRef = this.dialog.open(this.otpTemplate, {
      disableClose: true,
      width: '400px'
    });
  }

  closeOtpDialog(): void {
    this.otpDialogRef.close();
  }


  saveEmail(){
    this.contactType = 'email';
    this.disableEmail = true;
    this.openOtpDialog();
    this.snackbar.openSuccessSnackBar('Otp Sent to email ')
  }

  saveMobileNumber(){
    this.contactType = 'mobileNumber';
    this.disableMobileNumber = true;
    this.openOtpDialog();
    this.snackbar.openSuccessSnackBar('Otp Sent to number ')
  }






  sendOtp(){
    // console.log(this.otp.value);
    
  }

  submitOtp(){
    
    console.log(this.myForm.value.otp);
    
  }


}
