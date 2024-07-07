import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA, MatDialogModule } from '@angular/material/dialog'; // Import MatDialogRef and MAT_DIALOG_DATA
import {MatInputModule} from '@angular/material/input';


@Component({
  selector: 'app-otpmodel',
  standalone: true,
  imports: [
    MatDialogModule,
    FormsModule,
    MatInputModule],
  templateUrl: './otpmodel.component.html',
  styleUrl: './otpmodel.component.css'
})
export class OtpmodelComponent {

  otp: string = '';

  constructor(
    public dialogRef: MatDialogRef<OtpmodelComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {}

  onCancel(): void {
    this.dialogRef.close();
  }

  onVerify(): void {
    this.dialogRef.close(this.otp);
  }

}
