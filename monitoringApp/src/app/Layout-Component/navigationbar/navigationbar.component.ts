import { Component } from '@angular/core';
import { AuthentificationService } from '../../services/authentification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navigationbar',
  templateUrl: './navigationbar.component.html',
  styleUrls: ['./navigationbar.component.css']  // Fix typo from 'styleUrl' to 'styleUrls'
})
export class NavigationbarComponent {

  constructor(private authService: AuthentificationService, private router: Router) {}

  logout() {
    this.authService.clearAuthToken();
    this.router.navigate(['/login']);  // Navigate to login page or any other desired page
  }
}
