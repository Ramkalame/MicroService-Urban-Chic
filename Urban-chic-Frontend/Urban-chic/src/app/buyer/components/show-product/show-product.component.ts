import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { CategoriesComponent } from "../categories/categories.component";
import { NavbarComponent } from "../navbar/navbar.component";
import { FooterComponent } from "../footer/footer.component";

@Component({
  selector: 'app-show-product',
  standalone: true,
  imports: [CommonModule, CategoriesComponent, NavbarComponent, FooterComponent],
  templateUrl: './show-product.component.html',
  styleUrl: './show-product.component.css'
})
export class ShowProductComponent {

}
