import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../Models/Logindto';
import { Observable } from 'rxjs';
import { SignupDto } from '../Models/Signupdto';
import { AxiosService } from './axios.service';
import { from } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private baseUrl = 'http://localhost:8089/api';

  constructor(private axiosService: AxiosService) { }

  login(loginRequest: LoginDto): Observable<any> {
    return from(
      this.axiosService.request('POST', `${this.baseUrl}/users/login`, loginRequest).then(response => {
        if (response.data.token) {
          this.axiosService.setAuthToken(response.data.token); // Save the token if present
        }
        return response.data;
      })
    );
  }
  signup(signupRequest: SignupDto): Observable<any> {
    return from(
      this.axiosService.request('POST', `${this.baseUrl}/users/add`, signupRequest).then(response => {
        this.axiosService.setAuthToken(response.data.token); // Save the token if present

      })
    );
  }
  //2 methods to manage the auth token storage
  /*
  getAuthToken():string | null   {
    return window.localStorage.getItem("auth_token");
  }
  //add the token in the local storage
  setAuthToken(token :string | null):void   {
    if (token!=null) 
      window.localStorage.setItem("auth_token",token);
    else 
    window.localStorage.removeItem("auth_token");

  }*/
}
