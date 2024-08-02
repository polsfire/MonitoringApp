import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginDto } from '../Models/Logindto';
import { Observable } from 'rxjs';
import { SignupDto } from '../Models/Signupdto';

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {

  private baseUrl = 'http://localhost:8089/api';

  constructor(private http: HttpClient) { }

  login(loginRequest: LoginDto): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.baseUrl}/users/login`, loginRequest, { headers });
  }

  signup(signupRequest: SignupDto): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<any>(`${this.baseUrl}/users/add`, signupRequest, { headers });
  }
  getAuthToken():string | null   {
    return window.localStorage.getItem("auth_token");
  }
  setAuthToken(token :string | null):void   {
    if (token!=null) 
      window.localStorage.setItem("auth_token",token);
    else 
    window.localStorage.removeItem("auth_token");

  }
}
