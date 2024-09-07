import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, Component, signal } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-product-management',
  standalone: true,
  imports: [MatGridListModule,
    MatIconModule,
  MatFormFieldModule,
  MatInputModule,
  CommonModule,
  FormsModule,
  ReactiveFormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './product-management.component.html',
  styleUrl: './product-management.component.css'
})
export class ProductManagementComponent {

  //form data 
  productName = signal('');
  productDescription = signal('');
  productPrice = signal('');
  productQuantity = signal('');
  productCategory = signal('');
  productSubcategory = signal('');
  productType = signal('');

  constructor(){};

  

  submitProductForm(){
    console.log(this.productName());  
    //implement form submission logic here
  }

}
