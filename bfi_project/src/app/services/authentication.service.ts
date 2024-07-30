import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { signupDto } from '../Model/signupDto';
import { LoginDto } from '../Model/LoginDto';
@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private baseUrl = 'http://localhost:8089/api'

  constructor(    private http: HttpClient  ) { }
  register(registerRequest: signupDto) 
  {
    console.log("send sucessfuly");

    return this.http.post<signupDto>
    (`${this.baseUrl}/users/add`, registerRequest);

  }
  login(LoginDto: LoginDto) 
  {
    console.log("login credentials send sucessfuly");
    console.log(LoginDto);
//azeaze
    return this.http.post<signupDto>
    (`${this.baseUrl}/users/login`, LoginDto);

  }  
}
