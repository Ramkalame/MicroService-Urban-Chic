import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { ApiResponse } from '../../core/models/shared-models/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class SnackbarService {

  constructor(private snackBar:MatSnackBar) { }

  //success message
  openSuccessSnackBar(message: string) {
    const config: MatSnackBarConfig = {
      duration: 3000,
      panelClass: ['success-snackbar'],
    };
    this.snackBar.open(message, 'Close',config);
  }

  //failed message
  openFailedSnackBar(error: HttpErrorResponse) {
    const config: MatSnackBarConfig = {
      duration: 3000,
      panelClass: ['failed-snackbar'],
    };
    const errorResponse = error.error as ApiResponse<null>;
    this.snackBar.open(errorResponse.message, 'Close',config);
  }

  openNonApiFailedSnackBar(error: HttpErrorResponse) {
    const config: MatSnackBarConfig = {
      duration: 3000,
      panelClass: ['failed-snackbar'],
    };
    this.snackBar.open(error.message, 'Close',config);
  }
}
