import { Component } from '@angular/core';
import { MetricsService } from '../../services/metrics.service';

@Component({
  selector: 'app-display-result',
  templateUrl: './display-result.component.html',
  styleUrl: './display-result.component.css'
})
export class DisplayResultComponent {

  parameterValues: { [key: string]: string[] } = {}; // Initialize with an empty object
    concatenatedMessage!:string;
    constructor(private parameterService: MetricsService) {
      this.parameterValues = this.parameterService.parameterValues; // Assign parameterValues from service
    }

    sendParameterValues() {
      this.parameterService.sendParameterValuesToBackend().subscribe(
          response => {
              console.log('Parameter values sent successfully:', response);

              if (response ) {
                  const concatenatedMessage = this.concatenateParameters(response);
                  console.log('Concatenated message:', concatenatedMessage);
                  this.concatenatedMessage=concatenatedMessage;
                  // Optionally, perform any actions with the concatenated message
              }
          },
          error => {
              console.error('Error sending parameter values:', error);
              // Handle error scenarios
          }
      );
  }

  private concatenateParameters(parameters: { [key: string]: any }): string {
    let concatenatedMessage = '';
    console.log('Starting concatenation of parameters:', parameters);

    for (const key in parameters) {
        if (parameters.hasOwnProperty(key)) {
            console.log(`Processing parameter: ${key}`);
            const values = parameters[key];
            console.log(`Values for ${key}:`, values);

            if (Array.isArray(values)) {
                concatenatedMessage += `${key}: ${values.join(' ')}; `;
            } else {
                // Handle non-array values or provide a default format
                console.warn(`Expected an array for ${key}, but got:`, values);
                concatenatedMessage += `${key}: ${values}; `;
            }
        }
    }

    console.log('Final concatenated message:', concatenatedMessage.trim());
    return concatenatedMessage.trim();
  }




    addValue(parameter: string) {
      if (parameter in this.parameterValues) {
        this.parameterValues[parameter].push("none"); // Add "none" value
      }
    }
    addErrorValue(parameter: string) {
      if (parameter in this.parameterValues) {
        this.parameterValues[parameter].push("error"); // Add "error" value
      }
    }
  }

