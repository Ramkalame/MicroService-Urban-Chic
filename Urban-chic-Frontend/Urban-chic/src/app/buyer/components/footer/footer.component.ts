import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import {FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import {faFacebook,faTwitter,faYoutube,faInstagram} from '@fortawesome/free-brands-svg-icons';

@Component({
  selector: 'app-footer',
  standalone: true,
  imports: [MatIconModule,FontAwesomeModule,],
  templateUrl: './footer.component.html',
  styleUrl: './footer.component.css'
})
export class FooterComponent {
  faFacebook =faFacebook;
  faTwitter = faTwitter;
  faYoutube = faYoutube;
  faInstagram = faInstagram

}
