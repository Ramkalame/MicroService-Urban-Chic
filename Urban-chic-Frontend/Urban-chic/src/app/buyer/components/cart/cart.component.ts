import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CommonModule, NavbarComponent,MatIconModule,MatButtonModule,RouterModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.css'
})
export class CartComponent {

  quantity:number = 1;

  constructor(){}

  increaseQuantity(){
    if(this.quantity<20) this.quantity++;
  }

  decreaseQuantity(){
    if(this.quantity > 1) this.quantity--;
  }
  
}
