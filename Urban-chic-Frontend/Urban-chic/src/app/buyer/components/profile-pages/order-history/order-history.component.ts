import { CommonModule } from '@angular/common';
import { Component, TemplateRef, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-order-history',
  standalone: true,
  imports: [CommonModule,MatDialogModule,MatButtonModule,MatIconModule,MatMenuModule,RouterModule],
  templateUrl: './order-history.component.html',
  styleUrl: './order-history.component.css'
})
export class OrderHistoryComponent {

  @ViewChild('deleteOrderHistoryTemplate') deleteOrderHistoryTemplate!:TemplateRef<any>;
  cancelDialogRef!:MatDialogRef<any>;

  constructor(private dialog:MatDialog){

  }


  openOrderHIstoryDeleteDialog(){    
    this.cancelDialogRef = this.dialog.open(this.deleteOrderHistoryTemplate, {
      width: '400px',
      disableClose: true
    });
  }

  closeOrderHistoryDeleteDialog(){
    this.cancelDialogRef.close();
  }

}
