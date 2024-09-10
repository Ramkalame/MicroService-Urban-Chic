import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-spinner',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './spinner.component.html',
  styleUrl: './spinner.component.css'
})
export class SpinnerComponent {
   isLoading = false;

   constructor(){
    this.showSpinnerForDuration(500)
   }
   showSpinnerForDuration(duration: number): void {
    this.isLoading = true; // Show the spinner
    setTimeout(() => {
      this.isLoading = false; // Hide the spinner after the specified duration
    }, duration);
  }

}
