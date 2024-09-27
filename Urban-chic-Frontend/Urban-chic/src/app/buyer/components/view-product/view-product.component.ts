import { Component, inject } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import {
  carouselImage,
  CustomCarouselComponent,
} from '../../../common/components/custom-carousel/custom-carousel.component';
import { SnackbarService } from '../../../common/services/snackbar.service';
import {MatProgressBarModule} from '@angular/material/progress-bar';



@Component({
  selector: 'app-view-product',
  standalone: true,
  imports: [
    NavbarComponent,
    RouterModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    CustomCarouselComponent,
    MatProgressBarModule
  ],
  templateUrl: './view-product.component.html',
  styleUrl: './view-product.component.css',
})
export class ViewProductComponent {
  snackBarService = inject(SnackbarService);

  images: carouselImage[] = [
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/b96848b7acd10dafde32203d12f6fea7_pzokzm.png',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/f8085f0019df6133fb41ee707f45aa51_s5d2by.png',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/e2566b9853071dbb7fe9306713bbe51f_vhzua9.png',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/3477aec265a0347bae41a8b34182b69b_d4vb7a.png',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/60f7a7c75fce4100a6d8053a9d86a0eb_omhyj0.png',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727289544/taking-photos-3672048_1280_dvk9nd.jpg',
    },
    {
      imageSrc:
        'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727289544/cellular-4599956_1280_tk8m6x.jpg',
    },
  ];

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
  

  share() {
    if (navigator.share) {
      navigator
        .share({
          title: 'Web Share API in Angular 18',
          text: 'Check out this feature!',
          url: 'https://google.com', // Use a simple URL for testing
        })
        .then(() => console.log('Content shared successfully!'))
        .catch((error) => alert('Encountered error while sharing.'));
    } else {
      // alert('Web Share API not supported in your browser.');
      navigator.clipboard
        .writeText('sample text copied to clipboard')
        .then(() => {
          console.log('Text copied to clipboard!');
          this.snackBarService.openSuccessSnackBar('Copied to clipboard!');
        });
    }
  }
  
}
