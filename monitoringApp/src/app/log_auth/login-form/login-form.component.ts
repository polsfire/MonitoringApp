import { Component, Output, EventEmitter } from '@angular/core'; 
 import { SignupDto } from '../../Models/Signupdto';
import { LoginDto } from '../../Models/Logindto';
  
 
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent {
  @Output() onSubmitLoginevent = new EventEmitter<LoginDto>();
  @Output() customEvent = new EventEmitter<string>();
  @Output() onSubmitRegisterEvent = new EventEmitter<SignupDto>();

  loginDto: LoginDto = {
    email: '',
    password: ''
  };
  signupDto: SignupDto = {
    username: '',
    firstname: '',
    lastname: '',
    email: '',
    password: ''
  };
  isRegistering: boolean = false;

  onSubmitLogin(): void {
    this.onSubmitLoginevent.emit(this.loginDto);
    console.log("send to the next", this.loginDto); // Log the emitted value
  }

  onSubmitRegister(): void {
    this.onSubmitRegisterEvent.emit(this.signupDto);
    console.log("Registering user:", this.signupDto); // Log the emitted value
  }

  switchToRegister(): void {
    this.isRegistering = true;
  }

  switchToLogin(): void {
    this.isRegistering = false;
  }
}
