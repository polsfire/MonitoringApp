import { Component } from '@angular/core';
import { MetricsService } from '../../services/metrics.service';

@Component({
  selector: 'app-user-pick-up-service',
  templateUrl: './user-pick-up-service.component.html',
  styleUrl: './user-pick-up-service.component.css'
})
export class UserPickUpServiceComponent {

  parameters: string[] = [];
  selectedParameter: string = '';

  constructor(private parameterService: MetricsService) {}

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

 
}

