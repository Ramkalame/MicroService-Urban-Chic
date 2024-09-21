import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-widget-top',
  standalone: true,
  imports: [MatIconModule,MatButtonModule],
  templateUrl: './widget-top.component.html',
  styleUrl: './widget-top.component.css'
})
export class WidgetTopComponent {

}
