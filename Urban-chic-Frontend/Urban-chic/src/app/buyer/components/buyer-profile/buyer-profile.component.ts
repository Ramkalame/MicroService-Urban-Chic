import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { MatMenuModule } from '@angular/material/menu';
import { MatListModule } from '@angular/material/list';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-buyer-profile',
  standalone: true,
  imports: [CommonModule, NavbarComponent,MatMenuModule,MatListModule,MatIconModule,MatButtonModule],
  templateUrl: './buyer-profile.component.html',
  styleUrl: './buyer-profile.component.css'
})
export class BuyerProfileComponent {

}
