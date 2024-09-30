import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
import { carouselImage } from '../../../common/components/custom-carousel/custom-carousel.component';

@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule,MatIconModule,MatButtonModule,RouterModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent {

  loop = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17];

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



}
