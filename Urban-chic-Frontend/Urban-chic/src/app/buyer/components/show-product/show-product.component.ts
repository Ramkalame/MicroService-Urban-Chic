import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CategoriesComponent } from "../categories/categories.component";
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";
import { ProductComponent } from "../product/product.component";
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-show-product',
  standalone: true,
  imports: [CommonModule, CategoriesComponent, NavbarComponent, FooterComponent, ProductComponent,MatButtonModule
    ,MatIconModule,MatMenuModule
  ],
  templateUrl: './show-product.component.html',
  styleUrl: './show-product.component.css'
})
export class ShowProductComponent {

}
