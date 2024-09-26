import { CommonModule } from '@angular/common';
import { Component, Input, OnDestroy, OnInit } from '@angular/core';

export interface carouselImage {
  imageSrc: string;
}

@Component({
  selector: 'app-custom-carousel',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './custom-carousel.component.html',
  styleUrl: './custom-carousel.component.css'
})
export class CustomCarouselComponent implements OnInit, OnDestroy {


  @Input() images: carouselImage[] = [];
  @Input() indicators: boolean = true;
  @Input() autoSlide: boolean = false;
  @Input() slideInterval: number = 5000;

  selectedIndex: number = 0;
  private autoSlideInterval: any;

  //swipe data
  private swipeCoord: [number, number] = [0, 0];
  private swipeTime: number = new Date().getTime();

  ngOnInit(): void {
    // Ensure autoSlide only starts if there are images
    if (this.autoSlide && this.images.length > 0) {
      this.autoSlideImages();
    }
  }

  ngOnDestroy(): void {
    // Clear the interval to prevent memory leaks
    if (this.autoSlideInterval) {
      clearInterval(this.autoSlideInterval);
    }
  }

  autoSlideImages() {
    this.autoSlideInterval = setInterval(() => {
      this.onNextClick();
    }, this.slideInterval);
  }

  selectImage(index: number) {
    this.selectedIndex = index;
  }

  onPrevClick() {
    this.selectedIndex = (this.selectedIndex === 0) ? this.images.length - 1 : this.selectedIndex - 1;
  }

  onNextClick() {
    this.selectedIndex = (this.selectedIndex === this.images.length - 1) ? 0 : this.selectedIndex + 1;
  }
  

  onSwipe(e: TouchEvent, when: string) {
    const coord: [number, number] = [e.changedTouches[0].clientX, e.changedTouches[0].clientY];
    const time = new Date().getTime();

    if (when === 'start') {
      this.swipeCoord = coord;
      this.swipeTime = time;
    } else if (when === 'end') {
      const direction = [coord[0] - this.swipeCoord[0], coord[1] - this.swipeCoord[1]];
      const duration = time - this.swipeTime;

      // Check if the swipe is horizontal and within time and length limits
      if (duration < 1000 &&
        Math.abs(direction[0]) > 30 && // Long enough
        Math.abs(direction[0]) > Math.abs(direction[1] * 3)) { // Horizontal enough
        
        if (direction[0] < 0) {
            // Swipe left - next image
            this.onNextClick();
        } else {
            // Swipe right - previous image
            this.onPrevClick();
        }
    }
    }
  }

}
