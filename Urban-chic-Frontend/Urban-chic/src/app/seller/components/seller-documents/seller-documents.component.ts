import { C } from '@angular/cdk/keycodes';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { numericValidator } from '../../../common/validators/NumericValidator';
import { SellerService } from '../../services/seller.service';
import { SnackbarService } from '../../../common/services/snackbar.service';
import { ApiResponse } from '../../../core/models/shared-models/api-response.model';
import { ImageUploadResponse } from '../../models/image-upload-response.model';
import { SellerDocumentRequest } from '../../models/seller-documents-request.model';
import { SellerDocument } from '../../models/seller-document.model';
import { Router } from '@angular/router';
import { AuthServiceService } from '../../../auth/services/auth.service';

@Component({
  selector: 'app-seller-documents',
  standalone: true,
  imports: [
    MatCardModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatButtonModule,
    MatLabel,
    ReactiveFormsModule,
    CommonModule,
  ],
  templateUrl: './seller-documents.component.html',
  styleUrl: './seller-documents.component.css',
})
export class SellerDocumentsComponent implements OnInit {
  allCategories = true;
  onlyBooks = true;
  selectedFile!: File;

  sellerForm!: FormGroup;
  fileError: string | null = null;

  authService = inject(AuthServiceService);
  sellerService = inject(SellerService);
  snackBar = inject(SnackbarService);
  router = inject(Router);

  //others
  imageUploadResponse!: ImageUploadResponse;
  userId!: string;

  constructor() {
    this.sellerForm = new FormGroup({
      companyName: new FormControl('', Validators.required),
      gstNumber: new FormControl('', [
        Validators.required,
        Validators.pattern(
          /[0-9]{2}[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}/
        ),
      ]),
      panNumber: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/),
      ]),
      accountNumber: new FormControl('', [
        Validators.required,
        Validators.minLength(9),
        Validators.maxLength(18),
        numericValidator(),
      ]),
      ifscCode: new FormControl('', [
        Validators.required,
        Validators.pattern(/^[A-Z]{4}0[A-Z0-9]{6}$/),
      ]),
      street: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      postalCode: new FormControl('', [
        Validators.required,
        Validators.pattern(/^\d{6}$/),
        numericValidator(),
      ]),
    });
  }

  ngOnInit(): void {
    const isUserIdNull: string = this.authService.getUserId();
    if (isUserIdNull.length == 0) {
      this.router.navigate(['/auth/login/seller']);
    } else {
      this.userId = isUserIdNull;
    }
  }

  onlyBooksBtn() {
    this.allCategories = false;
    this.onlyBooks = true;
  }

  allCategoriesBtn() {
    this.allCategories = true;
    this.onlyBooks = true;
  }

  onSelectFile(event: any) {
    const file: File = event.target.files[0];
    if (file) {
      if (file.type.startsWith('image/')) {
        this.selectedFile = file;
        this.fileError = null;
      } else {
        this.fileError = 'Please select a valid image file.';
        event.target.value = null;
      }
    }
  }

  uploadIMage() {
    console.log('uploading...');
    this.sellerService.uploadImage(this.selectedFile).subscribe({
      next: (res: ApiResponse<any>) => {
        this.imageUploadResponse = res.data;
        this.snackBar.openSuccessSnackBar(res.message);
        console.log(res);
      },
      error: (error: any) => {
        console.error(error);
        this.snackBar.openFailedSnackBar(error);
      },
    });
  }

  submitForm() {
    console.log(this.sellerForm.value);
    const form: SellerDocumentRequest = {
      companyName: this.sellerForm.value.companyName,
      companyLogoUrl: this.imageUploadResponse.publicUrl,
      sellerAddress: {
        street: this.sellerForm.value.street,
        city: this.sellerForm.value.city,
        state: this.sellerForm.value.state,
        country: this.sellerForm.value.country,
        postalCode: this.sellerForm.value.postalCode,
        sellerId: this.userId,
      },
      companyLogoPublicId: this.imageUploadResponse.publicId,
      gstNumber: this.sellerForm.value.gstNumber,
      panNumber: this.sellerForm.value.panNumber,
      accountNumber: this.sellerForm.value.accountNumber,
      ifscCode: this.sellerForm.value.ifscCode,
      sellerId: this.userId, // TODO: get from logged in user
    };

    this.sellerService.addSellerDocuments(form).subscribe({
      next: (res: ApiResponse<SellerDocument>) => {
        this.snackBar.openSuccessSnackBar(res.message);
        this.router.navigate(['/seller/dashboard']);
        console.log(res);
      },
      error: (error: any) => {
        console.error(error);
        this.snackBar.openFailedSnackBar(error);
      },
    });
  }
}
