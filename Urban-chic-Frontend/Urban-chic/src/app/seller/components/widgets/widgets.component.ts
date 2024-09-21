import { Component, inject, input, signal } from '@angular/core';
import { Widget } from '../../models/dashboard.model';
import { NgComponentOutlet } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { DashboardService } from '../../services/dashboard.service';

@Component({
  selector: 'app-widgets',
  standalone: true,
  imports: [NgComponentOutlet, MatButtonModule, MatIconModule,],
  templateUrl: './widgets.component.html',
  styleUrl: './widgets.component.css'
})
export class WidgetsComponent {
  
  dashboard =  inject(DashboardService);

  data = input.required<Widget>();

  showOptions = signal(false)

}
