import { DialogRef } from '@angular/cdk/dialog';
import { CommonModule } from '@angular/common';
import { Component, Input, TemplateRef, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule,MatToolbarModule,MatButtonModule,MatIconModule,RouterModule,MatDialogModule,
    MatMenuModule
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  @Input() showLogo: boolean = true;
  @Input() showCartButton: boolean = true;
  @Input() showFavoriteButton: boolean = true;
  @Input() showProfileButton: boolean = true;
  @Input() showSearchBar: boolean = true;
  @Input() showBecomeASellerButton: boolean = true;
  @Input() showMenuButton: boolean = true;

  isLoggedIn: boolean = true;



  constructor(private dialog:MatDialog){}



}
