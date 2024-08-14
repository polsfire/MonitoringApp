import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';
import { AuthentificationService } from '../../services/authentification.service';
import { LoginDto } from '../../Models/Logindto';
import { SignupDto } from '../../Models/Signupdto';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {
  @Output() loginSuccess = new EventEmitter<void>();

  constructor(
    private router: Router,
    private loginrequest: AuthentificationService
  ) {
    if (!loginrequest) {
      console.error('AuthenticationService is not provided.');
    }
  }
  onLogin(input: LoginDto): void {
    console.log('Login attempt with input:', input); // Detailed log message

    this.loginrequest.login(input)
      .subscribe({
        next: () => {
          console.log('Login successful, navigating to home.');
          this.loginSuccess.emit(); // Emit the event to notify the AppComponent
          this.router.navigate(['/']);
        },
        error: err => {
          console.error('Login error:', err); // Log the detailed error for debugging

          let errorMessage = 'An unknown error occurred. Please try again later.';

          // Check for specific errors based on status code
          if (err.status === 404) {
            errorMessage = 'User not found: Please check your email address.';
          } else if (err.status === 401) {
            errorMessage = 'Invalid password: Please try again.';
          } else if (err.status === 0) {
            errorMessage = 'Network error: Please check your internet connection.';
          } else if (err.status >= 500) {
            errorMessage = 'Server error: Please try again later.';
          } else if (err.error && err.error.message) {
            errorMessage = `Error: ${err.error.message}`;
          }

          alert(errorMessage); // Display a user-friendly error message
        }
      });
}





  onRegister(input: SignupDto): void {
    console.log('received', input); // Log the received value

    this.loginrequest.signup(input).subscribe({
      next: () => {
        this.loginSuccess.emit(); // Emit the event to notify the AppComponent
        this.router.navigate(['/']);
      },
      error: err => {
        console.error('Error:', err); // Log the error for debugging
        if (err.error && err.error.message) {
          alert('Error: ' + err.error.message); // Display the error message to the user
        } else {
          alert('An unknown error occurred.'); // Fallback if no specific error message is available
        }
      }
    });
  }
}
