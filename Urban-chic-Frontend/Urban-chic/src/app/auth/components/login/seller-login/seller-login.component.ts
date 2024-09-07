import {
  ChangeDetectionStrategy,
  Component,
  inject,
  signal,
} from '@angular/core';
import {
  FormControl,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { Router } from '@angular/router';
import { merge } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { CommonModule } from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { HttpErrorResponse } from '@angular/common/http';
import { SnackbarService } from '../../../../common/services/snackbar.service';
import { AuthServiceService } from '../../../services/auth.service';
import { SellerLoginRequest } from '../../../models/seller-login.model';
import { ApiResponse } from '../../../../core/models/shared-models/api-response.model';
import { LoginResponse } from '../../../models/login-response.model';
import { SellerAccountStatus } from '../../../../core/enums/seller-account-status';

@Component({
  selector: 'app-seller-login',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    MatFormFieldModule,
    MatIconModule,
    MatInputModule,
    MatLabel,
    MatButtonModule,
  ],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './seller-login.component.html',
  styleUrl: './seller-login.component.css',
})
export class SellerLoginComponent {
  //form data
  readonly email = new FormControl('', [Validators.required, Validators.email]);
  readonly password = new FormControl('', [Validators.required]);

  //error signals
  emailError = signal('');
  passwordError = signal('');

  //other signals
  hide = signal(true);
  private snackbar = inject(SnackbarService);

  constructor(private router: Router, private authService: AuthServiceService) {
    merge(
      this.email.statusChanges,
      this.email.valueChanges,
      this.password.statusChanges,
      this.password.valueChanges
    )
      .pipe(takeUntilDestroyed())
      .subscribe(() => this.updateErrorMessage());
  }

  //to hide the password
  clickEvent(event: MouseEvent) {
    this.hide.set(!this.hide());
    event.stopPropagation();
  }

  //to update error messages
  updateErrorMessage() {
    //email error
    if (this.email.hasError('required')) {
      this.emailError.set('Email is required');
    } else if (this.email.hasError('email')) {
      this.emailError.set('Not a valid email');
    } else {
      this.emailError.set('');
    }

    //password error
    if (this.password.hasError('required')) {
      this.passwordError.set('Password is required');
    } else {
      this.passwordError.set('');
    }
  }

  //login api
  sellerLogin() {
    const data: SellerLoginRequest = {
      userName: this.email.value!,
      password: this.password.value!,
    };
    console.log(data);
    this.authService.sellerLogin(data).subscribe({
      next: (response: ApiResponse<LoginResponse>) => {
        console.log(response);
        //storing the token to local storage
        localStorage.setItem('token', response.data.jwtToken);
        //updating the logged in status in the service
        this.snackbar.openSuccessSnackBar('Login successful');
        if (
          response.data.userAccountStatus ===
          SellerAccountStatus.DOCUMENTS_VERIFIED
        ) {
          this.router.navigate(['/seller/dashboard']);
        } else {
          this.router.navigate(['/seller/documents']);
        }
      },
      error: (error: HttpErrorResponse) => {
        console.log(error);
        this.snackbar.openFailedSnackBar(error);
      },
    });
  }
}
