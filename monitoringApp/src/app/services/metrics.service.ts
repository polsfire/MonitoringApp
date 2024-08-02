import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MetricsService {
  parameterValues: { [key: string]: string[] } = {};
  
  baseUrl = 'http://localhost:8089/api'; // Replace with your actual backend base URL

  constructor(private http: HttpClient) {}



  getParameters(): string[] {
    return Object.keys(this.parameterValues);
  }

  addParameter(parameter: string) {
    if (!(parameter in this.parameterValues)) {
      this.parameterValues[parameter] = []; // Initialize with an empty array
      console.log(`Added new parameter: ${parameter}`); // Log the added parameter
    }
    console.log('Parameter Values:', this.parameterValues); // Log the updated parameter values table
  }

  addParameterValue(parameter: string, value: string) {
    if (parameter in this.parameterValues) {
      this.parameterValues[parameter].push(value);
    } else {
      this.parameterValues[parameter] = [value];
    }
  }

  getParameterValues(parameter: string): string[] | undefined {
    return this.parameterValues[parameter];
  }

  // Example: Method to clear parameter values
  clearParameterValues(parameter: string) {
    if (parameter in this.parameterValues) {
      this.parameterValues[parameter] = [];
    }
  }

  // backend---------communication
  sendParameterValuesToBackend(): Observable<any> {
    return this.http.post(`${this.baseUrl}/Metric/send-parameters`, this.parameterValues);
  }

  // Function to get the parameter list from the backend
  /*getParameterListFromBackend(): Observable<string[]> {
    return this.http.get<string[]>(`${this.baseUrl}/metric/parameter`);
  }*/
    getParameterListFromBackend(): Observable<string[]> {
      // Replace with your static list of parameter names
      const staticParameters: string[] = ['number of specific type file', 'Display the file names', 'errors'];
  
      return of(staticParameters); // Return an observable of the static parameter array
    }


  }
