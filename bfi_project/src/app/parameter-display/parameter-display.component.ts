import { Component } from '@angular/core';
import { ParameterService } from '../services/parameter.service';

@Component({
  selector: 'app-parameter-display',
  templateUrl: './parameter-display.component.html',
  styleUrls: ['./parameter-display.component.css']
})
export class ParameterDisplayComponent {
  parameterValues: { [key: string]: string[] } = {}; // Initialize with an empty object

  constructor(private parameterService: ParameterService) {
    this.parameterValues = this.parameterService.parameterValues; // Assign parameterValues from service
  }

  sendParameterValues() {
    this.parameterService.sendParameterValuesToBackend().subscribe(
      response => {
        console.log('Parameter values sent successfully:', response);
        // Optionally, perform any actions upon successful response from backend
      },
      error => {
        console.error('Error sending parameter values:', error);
        // Handle error scenarios
      }
    );
  }
  addValue(parameter: string) {
    if (parameter in this.parameterValues) {
      this.parameterValues[parameter].push("none"); // Add "none" value
    }
  }
}
