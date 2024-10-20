import { CommonModule } from '@angular/common';
import { Component, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { RouterModule } from '@angular/router';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { faGoogle } from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-buyer-login',
  standalone: true,
  imports: [
    MatInputModule,
    MatFormFieldModule,
    MatIconModule,
    MatButtonModule,
    FontAwesomeModule,
    MatDialogModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule,
  ],
  templateUrl: './buyer-login.component.html',
  styleUrl: './buyer-login.component.css'
})
export class BuyerLoginComponent {


  @ViewChild('otpTemplate') otpTemplate!:TemplateRef<any>;
  otpDialogRef!: MatDialogRef<any>;

  faGoogle = faGoogle;
  myForm!: FormGroup;

  constructor(private dialog:MatDialog,private fb:FormBuilder){
    this.myForm = this.fb.group({
      otp:new FormControl('',[
        Validators.required,
        Validators.pattern('^[0-9]*$'),
        Validators.minLength(4),
        Validators.maxLength(4)
      ]),
      phoneNumber:new FormControl('',[
        Validators.required,
        Validators.pattern('^[0-9]{10}$'),
      ])
    })
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

  submitOtp(){
    console.log('OTP submitted:', this.myForm.value.otp);
    this.myForm.setValue({
      otp: ''
    });
    this.closeOtpDialog();
  }

  // Helper method to check if control is invalid
  isControlInvalid(controlName: string) {
    const control = this.myForm.get(controlName);
    return control?.invalid && (control.dirty || control.touched);
  }


}
