import { DialogRef } from '@angular/cdk/dialog';
import { CommonModule } from '@angular/common';
import { AfterViewInit, Component, TemplateRef, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatDialog, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-my-orders',
  standalone: true,
  imports: [CommonModule,MatIconModule,MatButtonModule,MatMenuModule,MatDialogModule,RouterModule],
  templateUrl: './my-orders.component.html',
  styleUrl: './my-orders.component.css'
})
export class MyOrdersComponent implements AfterViewInit{

  @ViewChild('cancelOrderTemplate') cancelOrderTemplate!:TemplateRef<any>;
  cancelDialogRef!:MatDialogRef<any>;

  constructor(private dialog:MatDialog){

  }


  ngAfterViewInit(): void {
    console.log(this.cancelOrderTemplate);
    
  }
;

  

  openCancelOrderDialog(){    
    this.cancelDialogRef = this.dialog.open(this.cancelOrderTemplate, {
      width: '400px',
      disableClose: true
    });
  }

  closeCancelOrderDialog(){
    this.cancelDialogRef.close();
  }



}
