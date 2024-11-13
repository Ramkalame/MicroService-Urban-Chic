import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, TemplateRef, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import {
  MatDialog,
  MatDialogModule,
  MatDialogRef,
} from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { addressLabel } from '../../../../core/enums/gender';
import { Address, Buyer } from '../../../../auth/models/buyer.model';
import { BuyerService } from '../../../services/buyer.service';
import { BuyerAuthService } from '../../../../core/services/buyer-auth.service';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { SnackbarService } from '../../../../common/services/snackbar.service';

@Component({
  selector: 'app-manage-address',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatIconModule,
    MatButtonModule,
    ReactiveFormsModule,
    MatMenuModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
  ],
  templateUrl: './manage-address.component.html',
  styleUrl: './manage-address.component.css',
})
export class ManageAddressComponent implements OnInit {
  
  @ViewChild('deleteDialog') deleteDialog!: TemplateRef<any>;
  deleteDialogRef!: MatDialogRef<any>;
  addressForm!: FormGroup;
  addressLabel = addressLabel;
  addressList!:Address [];
  selectedAddressId!: string;

  //toggle properties
  showAddNewAddress: boolean = false;
  showEditAddress: boolean = false;
  disableCountry: boolean = true;


  //services injection
  buyerService = inject(BuyerService);
  buyerAuthService = inject(BuyerAuthService);
  snackBarService = inject(SnackbarService)

  constructor(private dialog: MatDialog, private fb: FormBuilder) {
    this.addressForm = fb.group({
      houseNumber: new FormControl(''),
      streetName: new FormControl('', [Validators.required]),
      landmark: new FormControl(''),
      city: new FormControl('', [Validators.required]),
      state: new FormControl('', [Validators.required]),
      district: new FormControl('', [Validators.required]),
      country: new FormControl({value:'India',disabled:this.disableCountry}),
      pincode: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9]{6}$'),
      ]),
      addressLabel: new FormControl('', [Validators.required]),
    });
  }
  ngOnInit(): void {
    this.getAllAddresses(); 
  }

  openDeleteDialog(addressId?: number): void {
    this.deleteDialogRef = this.dialog.open(this.deleteDialog, {
      data: { addressId },
    });
  }

  closeDeleteDialog(): void {
    this.deleteDialogRef.close();
  }
  showAddNewAddressDiv() {
    this.showAddNewAddress = true;
  }
  closeAddNewAddressDiv() {
    this.showAddNewAddress = false;
    this.addressForm.reset();
  }

  showEditAddressDiv(index:number) {
    this.showEditAddress = true;
    this.patchAddressFormById(index);
    this.selectedAddressId= this.addressList[index].id;
  }
  cancelEditAddress() {
    this.showEditAddress = false;
    this.addressForm.reset();
  }

  //api methods

  saveAddress(){
    this.addressForm.markAllAsTouched();

    if(this.addressForm.valid){
     const buyerId =  this.buyerAuthService.getUserId();
      const formData:Address={
        id: '',
        houseNumber: this.addressForm.value.houseNumber,
        streetName: this.addressForm.value.streetName,
        landmark: this.addressForm.value.landmark,
        city: this.addressForm.value.city,
        district: this.addressForm.value.district,
        state: this.addressForm.value.state,
        country: this.addressForm.value.country,
        pinCode: this.addressForm.value.pincode,
        addressLabel: this.addressForm.value.addressLabel,
      }
      this.buyerService.saveAddress(formData, buyerId).subscribe({
        next: (res: ApiResponse<Address>) => {
          this.getAllAddresses();
          this.showAddNewAddress = false;
          this.addressForm.reset();
          this.snackBarService.openSuccessSnackBar(res.message)
        },
        error: (err: any) => {
          console.error(err);
          this.snackBarService.openStringMsgFailedSnackBar('Something went wrong');
        }
      });
    }
  }

  getAllAddresses(){
    const buyerId = this.buyerAuthService.getUserId();
    this.buyerService.getAllAddresses(buyerId).subscribe({
      next: (res: ApiResponse<Address[]>) => {
        this.addressList = res.data;
      },
      error: (err: any) => {
        console.error(err);
        this.snackBarService.openStringMsgFailedSnackBar('Something went wrong');
      }
    });
  }

  deleteAddressById(id: string){
    const buyerId = this.buyerAuthService.getUserId();
    this.buyerService.deleteAddressById(id,buyerId).subscribe({
      next: (res: ApiResponse<any>) => {
        // this.closeDeleteDialog();
        this.snackBarService.openSuccessSnackBar(res.message)
        this.addressList = res.data;
      },
      error: (err: any) => {
        console.error(err);
        this.snackBarService.openStringMsgFailedSnackBar('Something went wrong');
      }
    });
  }

  patchAddressFormById(index:number){
      const selectedAddress:Address = this.addressList[index];
      this.addressForm.patchValue({
        houseNumber: selectedAddress.houseNumber,
        streetName: selectedAddress.streetName,
        landmark: selectedAddress.landmark,
        city: selectedAddress.city,
        state: selectedAddress.state,
        district: selectedAddress.district,
        country: selectedAddress.country,
        pincode: selectedAddress.pinCode,
        addressLabel: selectedAddress.addressLabel,
      })
  }

  updateAddressById(){
    const buyerId = this.buyerAuthService.getUserId();
    const formData:Address = {
      id: '',
      houseNumber: this.addressForm.value.houseNumber,
      streetName: this.addressForm.value.streetName,
      landmark: this.addressForm.value.landmark,
      city: this.addressForm.value.city,
      district: this.addressForm.value.district,
      state: this.addressForm.value.state,
      country: this.addressForm.value.country,
      pinCode: this.addressForm.value.pincode,
      addressLabel: this.addressForm.value.addressLabel,
    }    

    this.buyerService.updateAddressById(formData,this.selectedAddressId,buyerId).subscribe({
      next: (res: ApiResponse<Address[]>) => {
        this.addressList = res.data;
        this.showEditAddress = false;
        this.snackBarService.openSuccessSnackBar(res.message)
        this.addressForm.reset();
      },
      error: (err: any) => {
        console.error(err);
        this.snackBarService.openStringMsgFailedSnackBar('Something went wrong');
      }
    });

  }


}
