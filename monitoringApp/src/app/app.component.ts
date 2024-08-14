import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'monitoringApp';
  isAuthenticated: boolean = false; // This flag will determine what to display

  // Method to handle successful login
  onLoginSuccess(): void {
    this.isAuthenticated = true;
  }
}
