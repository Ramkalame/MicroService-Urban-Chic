import { Component, inject } from '@angular/core';
import { NavbarComponent } from "../navbar/navbar.component";
import { CommonModule, Location } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import {MatTabsModule} from '@angular/material/tabs';

@Component({
  selector: 'app-product-details',
  standalone: true,
  imports: [NavbarComponent,CommonModule,MatTabsModule,MatButtonModule,MatIconModule],
  templateUrl: './product-details.component.html',
  styleUrl: './product-details.component.css'
})
export class ProductDetailsComponent {

  location = inject(Location);

  smartphoneAttributes = [
    { attributeName: "Brand", attributeValue: "Samsung" },
    { attributeName: "Model", attributeValue: "Galaxy S23 Ultra" },
    { attributeName: "Display Size", attributeValue: "6.8 inches" },
    { attributeName: "Resolution", attributeValue: "1440 x 3088 pixels" },
    { attributeName: "Operating System", attributeValue: "Android 13" },
    { attributeName: "Processor", attributeValue: "Snapdragon 8 Gen 2" },
    { attributeName: "RAM", attributeValue: "12 GB" },
    { attributeName: "Internal Storage", attributeValue: "256 GB" },
    { attributeName: "Battery Capacity", attributeValue: "5000 mAh" },
    { attributeName: "Main Camera", attributeValue: "200 MP + 12 MP + 10 MP + 10 MP" },
    { attributeName: "Front Camera", attributeValue: "12 MP" },
    { attributeName: "Charging Speed", attributeValue: "45W Fast Charging" },
    { attributeName: "Weight", attributeValue: "234 grams" },
    { attributeName: "Connectivity", attributeValue: "5G, Wi-Fi 6, Bluetooth 5.3" },
    { attributeName: "Water Resistance", attributeValue: "IP68" },
  ];

  goBack(){
    this.location.back();
  }
  
}
