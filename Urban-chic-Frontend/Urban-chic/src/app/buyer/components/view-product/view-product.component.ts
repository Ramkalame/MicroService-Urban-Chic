import { Component } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import {NgbModule} from '@ng-bo';

@Component({
  selector: 'app-view-product',
  standalone: true,
  imports: [NavbarComponent,CommonModule,MatIconModule,MatButtonModule,],
  templateUrl: './view-product.component.html',
  styleUrl: './view-product.component.css'
})
export class ViewProductComponent {

}
