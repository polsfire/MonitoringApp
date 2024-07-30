import { Component } from '@angular/core';
import { Router } from '@angular/router'; 
import { AuthenticationService } from '../services/authentication.service';
import { LoginDto } from '../Model/LoginDto';

@Component({
  selector: 'app-content',
  templateUrl: './content.component.html',
  styleUrl: './content.component.css'
})
export class ContentComponent {
  constructor(
    private router: Router,
   private loginrequest: AuthenticationService
 ) {
   if (!loginrequest) {
     console.error('AuthenticationService is not provided.');
   }
 }
 handleCustomEvent(message: string) {
  console.log(message);
}
onLogin(input: LoginDto): void {
  console.log('received', input); // Log the received value

  this.loginrequest.login(input)
    .subscribe({
      next: () => {
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
