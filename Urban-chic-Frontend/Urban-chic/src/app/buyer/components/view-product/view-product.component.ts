import { Component } from '@angular/core';
import { NavbarComponent } from '../navbar/navbar.component';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { RouterModule } from '@angular/router';
import { carouselImage, CustomCarouselComponent } from "../../../common/components/custom-carousel/custom-carousel.component";

@Component({
  selector: 'app-view-product',
  standalone: true,
  imports: [
    NavbarComponent,
    RouterModule,
    CommonModule,
    MatIconModule,
    MatButtonModule,
    CustomCarouselComponent
  ],
  templateUrl: './view-product.component.html',
  styleUrl: './view-product.component.css',
})
export class ViewProductComponent {


  images:carouselImage[] = [
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/b96848b7acd10dafde32203d12f6fea7_pzokzm.png'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/f8085f0019df6133fb41ee707f45aa51_s5d2by.png'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/e2566b9853071dbb7fe9306713bbe51f_vhzua9.png'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/3477aec265a0347bae41a8b34182b69b_d4vb7a.png'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727379798/60f7a7c75fce4100a6d8053a9d86a0eb_omhyj0.png'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727289544/taking-photos-3672048_1280_dvk9nd.jpg'},
    {imageSrc:'https://res.cloudinary.com/dziu7iyz1/image/upload/v1727289544/cellular-4599956_1280_tk8m6x.jpg'},
  ];



}
