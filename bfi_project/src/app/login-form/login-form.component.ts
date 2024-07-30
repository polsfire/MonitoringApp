import { Component, Output,EventEmitter } from '@angular/core'; 
import { LoginDto } from '../Model/LoginDto';
import { signupDto } from '../Model/signupDto';
 
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.css'
})
export class LoginFormComponent {
  @Output() onSubmitLoginevent = new EventEmitter<LoginDto>();
  @Output() customEvent = new EventEmitter<string>();
  @Output() onSubmitRegisterEvent = new EventEmitter<signupDto>();


  LoginDto: LoginDto = {
    email: '',
    password: ''};
    signupDto: signupDto = {
      username: '',
      firstname: '',
      Lastname: '',
      email: '',
      password: ''
    };
    isRegistering: boolean = false;

    onSubmitLogin(): void {
    this.onSubmitLoginevent.emit(this.LoginDto);
    console.log("send to the next", this.LoginDto); // Log the emitted value
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
