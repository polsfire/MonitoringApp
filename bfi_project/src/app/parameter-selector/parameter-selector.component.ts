import { Component, OnInit } from '@angular/core';
import { ParameterService } from '../services/parameter.service';

@Component({
  selector: 'app-parameter-selector',
  templateUrl: './parameter-selector.component.html',
  styleUrls: ['./parameter-selector.component.css']
})
export class ParameterSelectorComponent implements OnInit {
  parameters: string[] = [];
  selectedParameter: string = '';

  constructor(private parameterService: ParameterService) {}

  ngOnInit() {
    this.getParameterListFromBackend();
  }

  getParameterListFromBackend() {
    this.parameterService.getParameterListFromBackend().subscribe(
      (data: string[]) => {
        this.parameters = data;
      },
      (error) => {
        console.error('Error fetching parameters:', error);
      }
    );
  }

  onParameterSelect() {
    if (this.selectedParameter) {
      this.parameterService.addParameter(this.selectedParameter);
      this.selectedParameter = '';
    }
  }

  // Optional: Method to send parameter values to backend
  // sendParameterValues() {
  //   this.parameterService.sendParameterValuesToBackend().subscribe(
  //     (response) => {
  //       console.log('Parameter values sent to backend:', response);
  //     },
  //     (error) => {
  //       console.error('Error sending parameter values:', error);
  //     }
  //   );
  // }
}
