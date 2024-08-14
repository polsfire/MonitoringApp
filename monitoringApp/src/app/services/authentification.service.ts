import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../Models/Logindto';
import { Observable, from, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { SignupDto } from '../Models/Signupdto';
import { AxiosService } from './axios.service';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private baseUrl = 'http://localhost:8089/api';

  constructor(private axiosService: AxiosService) { }

  clearAuthToken(): void {
    this.axiosService.setAuthToken(null);
  }

  login(loginRequest: LoginDto): Observable<any> {
    return from(
      this.axiosService.request('POST', `${this.baseUrl}/users/login`, loginRequest).then(response => {
        if (response.data.token) {
          this.axiosService.setAuthToken(response.data.token); // Save the token if present
        }
        return response.data;
      })
    ).pipe(
      catchError(error => {
        console.error('Login error:', error);
        let errorMessage = 'An unknown error occurred. Please try again later.';
        if (error.response && error.response.data && error.response.data.message) {
          errorMessage = error.response.data.message;
        }
        return throwError(() => new Error(errorMessage));
      })
    );
  }

  signup(signupRequest: SignupDto): Observable<any> {
    return from(
      this.axiosService.request('POST', `${this.baseUrl}/users/add`, signupRequest).then(response => {
        this.axiosService.setAuthToken(response.data.token); // Save the token if present
        return response.data;
      })
    ).pipe(
      catchError(error => {
        console.error('Signup error:', error);
        let errorMessage = 'An unknown error occurred. Please try again later.';
        if (error.response && error.response.data && error.response.data.message) {
          errorMessage = error.response.data.message;
        }
        return throwError(() => new Error(errorMessage));
      })
    );
  }
}
