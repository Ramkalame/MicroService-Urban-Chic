import { CommonModule } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-seller-registration',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './seller-registration.component.html',
  styleUrl: './seller-registration.component.css'
})
export class SellerRegistrationComponent {
  passwordBtnText ="Show";
  showPassword = false;
  password: string = '';
  isOpen:boolean = false;

  constructor(){

  }

  //---------------------- Non Api Method--------------------
  //method to open otp modal
  openModal(){
    this.isOpen = true;
  }
  //method to close otp modal
  closeModal(){
    this.isOpen = false;
  }

  //method to hide and show password
  togglePasswordVisibility(txt:string) {
    this.showPassword = !this.showPassword;
    if(txt == "Show"){
      this.passwordBtnText = "Hide";
    }
    else{
      this.passwordBtnText = "Show";
    }
  }



}
