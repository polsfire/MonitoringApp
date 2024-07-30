import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MetricServicesComponent } from './metric-services/metric-services.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ParameterDisplayComponent } from './parameter-display/parameter-display.component';
const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route
  { path: 'services', component: MetricServicesComponent },
  { path: 'about', component:LoginFormComponent  },
  { path: '', redirectTo: '/parameters', pathMatch: 'full' },
  { path: 'parameters', component: ParameterDisplayComponent },

  // Add more routes as needed
];
 
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule { }
