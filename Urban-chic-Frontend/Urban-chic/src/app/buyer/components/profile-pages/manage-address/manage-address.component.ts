import { CommonModule } from '@angular/common';
import { Component, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-manage-address',
  standalone: true,
  imports: [CommonModule,ReactiveFormsModule,MatIconModule,MatButtonModule,ReactiveFormsModule,MatMenuModule,
    MatDialogModule,MatFormFieldModule,MatInputModule
  ],
  templateUrl: './manage-address.component.html',
  styleUrl: './manage-address.component.css'
})
export class ManageAddressComponent {

  @ViewChild('deleteDialog') deleteDialog!:TemplateRef<any>;
  deleteDialogRef!: MatDialogRef<any>;
  addressForm!: FormGroup;

  //toggle properties
  showAddNewAddress: boolean = false;
  showEditAddress: boolean = false;



  constructor(private dialog:MatDialog,private fb:FormBuilder){
    this.addressForm = fb.group({
      street: new FormControl('',[Validators.required]),
      city: new FormControl('',[Validators.required]),
      state: new FormControl('',[Validators.required]),
      country: new FormControl('',[Validators.required]),
      pincode: new FormControl('',[Validators.required, Validators.pattern('^[0-9]{6}$')]),
      addressType: new FormControl('',[Validators.required]),
    });
  }

  openDeleteDialog(addressId?: number): void {
    this.deleteDialogRef = this.dialog.open(this.deleteDialog, {
      data: { addressId }
    });
  }

  closeDeleteDialog(): void {
    this.deleteDialogRef.close();
  }
  showAddNewAddressDiv(){
    this.showAddNewAddress = true;
  }
  closeAddNewAddressDiv(){
    this.showAddNewAddress = false;
    this.addressForm.reset();
  }

  showEditAddressDiv(){
    this.showEditAddress = true;
  }
  cancelEditAddress(){
    this.showEditAddress = false;
    this.addressForm.reset();
  }



  //api methods
  deleteAddress(addressId?: number): void {

  }
}
