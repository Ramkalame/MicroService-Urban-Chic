import { Component, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatDividerModule } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridList, MatGridTile, MatGridTileFooterCssMatStyler } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { numericValidator } from '../../../common/validators/NumericValidator';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-seller-profile',
  standalone: true,
  imports: [MatGridTile, MatGridList,
    MatGridTileFooterCssMatStyler,
    MatCardModule, MatButtonModule, MatIconModule,
    MatDividerModule, MatDialogModule,
    MatInputModule, MatFormFieldModule, CommonModule, ReactiveFormsModule],
  templateUrl: './seller-profile.component.html',
  styleUrl: './seller-profile.component.css'
})
export class SellerProfileComponent {

  //contact dialog
  @ViewChild('contactDialogTemplate') contactDialogTemplate!: TemplateRef<any>;
  contactDialogRef!: MatDialogRef<any>;

  @ViewChild('taxDialogTemplate') taxDialogTemplate!: TemplateRef<any>;
  taxDialogRef!: MatDialogRef<any>;

  @ViewChild('addressDialogTemplate') addressDialogTemplate!: TemplateRef<any>;
  addressDialogRef!: MatDialogRef<any>;

  @ViewChild('imageDialogTemplate') imageDialogTemplate!: TemplateRef<any>;
  imageDialogRef!: MatDialogRef<any>;


  //forms 
  contactForm!: FormGroup;
  taxForm!: FormGroup;
  addressForm!: FormGroup;

  //other
  previewUrl: string | ArrayBuffer | null = null; // Holds the preview image URL
  selectedFile: File | null = null;               // Holds the selected file


  constructor(public dialog: MatDialog) {
    this.contactForm = new FormGroup({
      fullName: new FormControl('', Validators.required),
      mobileNumber: new FormControl('', [Validators.required, numericValidator(), Validators.pattern('^[0-9]{10}$')]),
      email: new FormControl('', [Validators.required, Validators.email])
    });

    this.taxForm = new FormGroup({
      companyName: new FormControl('', Validators.required),
      gstNumber: new FormControl('', [Validators.required, Validators.pattern(
        /[0-9]{2}[A-Z]{3}[ABCFGHLJPTF]{1}[A-Z]{1}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}/
      )]),
      panNumber: new FormControl('', [Validators.required, Validators.pattern(/^[A-Z]{5}[0-9]{4}[A-Z]{1}$/)]),
      accountNumber: new FormControl('', [Validators.required, Validators.minLength(9),
      Validators.maxLength(18),
      numericValidator()]),
      ifscCode: new FormControl('', [Validators.required, Validators.pattern(/^[A-Z]{4}0[A-Z0-9]{6}$/)])
    });

    this.addressForm = new FormGroup({
      street: new FormControl('', Validators.required),
      city: new FormControl('', Validators.required),
      state: new FormControl('', Validators.required),
      country: new FormControl('', Validators.required),
      postalCode: new FormControl('', [Validators.required, numericValidator(), Validators.pattern('^[0-9]{6}$')])
    });

  }

  // contact dialog
  openContactDialog(): void {
    this.contactDialogRef = this.dialog.open(this.contactDialogTemplate, {
      width: '450px',
      disableClose: true
    });
  }

  closeContactDialog(): void {
    this.contactDialogRef.close();
    this.contactForm.reset();
  }

  // tax dialog
  openTaxDialog(): void {
    this.taxDialogRef = this.dialog.open(this.taxDialogTemplate, {
      width: '450px',
      disableClose: true
    });
  }
  closeTaxDialog(): void {
    this.taxDialogRef.close();
    this.taxForm.reset();

  }

  // address dialog
  openAddressDialog(): void {
    this.addressDialogRef = this.dialog.open(this.addressDialogTemplate, {
      width: '450px',
      disableClose: true
    });
  }
  closeAddressDialog(): void {
    this.addressDialogRef.close();
    this.addressForm.reset();
  }

  // image dialog
  openImageDialog(): void {
    this.imageDialogRef = this.dialog.open(this.imageDialogTemplate, {
      width: '450px',
      disableClose: true
    });
  }

  closeImageDialog(): void {
    this.imageDialogRef.close();
    this.previewUrl = null; // Clear the preview when the dialog is closed
    this.selectedFile = null; // Reset the selected file
  }

  //image preview
  // Triggered when a file is selected
  onFileSelected(event: Event): void {
    const fileInput = event.target as HTMLInputElement;
    if (fileInput.files && fileInput.files[0]) {
      this.selectedFile = fileInput.files[0];

      // Create a FileReader to read the image file and generate a preview
      const reader = new FileReader();
      reader.onload = () => {
        // Store the result (base64 URL of the image) in previewUrl
        this.previewUrl = reader.result;
      };

      // Read the selected file as a Data URL (base64 encoded)
      reader.readAsDataURL(this.selectedFile);
    }
  }


  //api methods for update
  submitContactForm() {
    console.log(this.contactForm.value);

  }

  submitTaxForm() {
    console.log(this.taxForm.value);

  }

  submitAddressForm() {
    console.log(this.addressForm.value);

  }

  submitImage() {
    console.log("image submitted");

  }

  //api methods for fetching data 
  fetchContactData() {
    //api call to fetch contact data
    //return data
  }
  fetchTaxData() {
    //api call to fetch tax data
    //return data
  }
  fetchAddressData() {
    //api call to fetch address data
    //return data
  }

}
