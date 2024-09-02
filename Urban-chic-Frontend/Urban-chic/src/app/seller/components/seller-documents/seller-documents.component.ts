import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatIcon, MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-seller-documents',
  standalone: true,
  imports: [MatCardModule,MatFormFieldModule,MatIconModule,MatInputModule,MatButtonModule,MatLabel],
  templateUrl: './seller-documents.component.html',
  styleUrl: './seller-documents.component.css'
})
export class SellerDocumentsComponent {
  

}
