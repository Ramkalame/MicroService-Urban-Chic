import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule } from '@angular/forms';
import {MatCardModule} from '@angular/material/card'
import {MatButtonModule} from '@angular/material/button'
import { MatFormFieldModule } from '@angular/material/form-field';

@Component({
  selector: 'app-seller-register',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatButtonModule, MatFormFieldModule, ReactiveFormsModule],
  templateUrl: './seller-register.component.html',
  styleUrl: './seller-register.component.css'
})
export class SellerRegisterComponent implements OnInit{

  companyDetailsForm:any;
  personalDetailsForm:any;
  documentUploadForm:any;

  constructor(private formBuilder:FormBuilder){}
  ngOnInit(): void {
    this.companyDetailsForm = this.formBuilder.group({})
    this.personalDetailsForm = this.formBuilder.group({})
    this.documentUploadForm = this.formBuilder.group({})
  }

  nextStep(){}
  previousStep(){}
  onFileSelected(event: any, field: string){}
  submit(){}


}
