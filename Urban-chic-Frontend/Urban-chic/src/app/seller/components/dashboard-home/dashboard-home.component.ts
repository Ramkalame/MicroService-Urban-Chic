import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { WidgetsComponent } from "../widgets/widgets.component";
import { DashboardService } from '../../services/dashboard.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-dashboard-home',
  standalone: true,
  imports: [CommonModule, WidgetsComponent,MatButtonModule,MatIconModule,MatMenuModule],
  providers:[DashboardService],
  templateUrl: './dashboard-home.component.html',
  styleUrl: './dashboard-home.component.css'
})
export class DashboardHomeComponent {

  dashboard =  inject(DashboardService);

}
