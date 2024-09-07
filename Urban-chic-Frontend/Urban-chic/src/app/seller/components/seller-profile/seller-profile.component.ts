import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDividerModule } from '@angular/material/divider';
import { MatGridList, MatGridTile, MatGridTileFooterCssMatStyler } from '@angular/material/grid-list';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-seller-profile',
  standalone: true,
  imports: [MatGridTile,MatGridList,MatGridTileFooterCssMatStyler,MatCardModule,MatButtonModule,MatIconModule,MatDividerModule],
  templateUrl: './seller-profile.component.html',
  styleUrl: './seller-profile.component.css'
})
export class SellerProfileComponent {

}
