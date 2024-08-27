import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { ContactComponent } from './components/contact/contact.component';
import { LogoutComponent } from './components/logout/logout.component';
import { FooterComponent } from './components/footer/footer.component';
import { OtpmodelComponent } from './otpmodel/otpmodel.component';
import { HttpClient } from '@angular/common/http';
import { SellerDashboardComponent } from './seller/components/seller-dashboard/seller-dashboard.component';
import { SellerRegisterComponent } from './seller/components/seller-register/seller-register.component';
import { SellerLoginComponent } from './seller/components/seller-login/seller-login.component';

@Component({
  selector: 'app-root',
  standalone: true,
  // if we import any component in this imprt then we can use that imported component at any where of my project section
  imports: [
    RouterOutlet,
    HeaderComponent,
    HomeComponent,
    LoginComponent,
    ContactComponent,
    LogoutComponent,
    FooterComponent,
    OtpmodelComponent,
    
// TODO: `HttpClientModule` should not be imported into a component directly.
// Please refactor the code to add `provideHttpClient()` call to the provider list in the
// application bootstrap logic and remove the `HttpClientModule` import from this component.
HttpClientModule,
    SellerDashboardComponent,
    SellerRegisterComponent,
    SellerLoginComponent
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Urban-Chic';
}
