import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-favourite',
  standalone: true,
  imports: [CommonModule, NavbarComponent,MatButtonModule,MatIconModule,MatMenuModule],
  templateUrl: './favourite.component.html',
  styleUrl: './favourite.component.css'
})
export class FavouriteComponent {

}
