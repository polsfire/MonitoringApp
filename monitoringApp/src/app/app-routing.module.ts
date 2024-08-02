import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayResultComponent } from './Metrics/display-result/display-result.component';
import { LinkComponent } from './Metrics/link/link.component';
import { ContentComponent } from './log_auth/content/content.component';

const routes: Routes = [

  { path: '', redirectTo: '/home', pathMatch: 'full' }, // Default route
  { path: 'services', component: LinkComponent },
  { path: 'about', component:ContentComponent  },
  { path: '', redirectTo: '/parameters', pathMatch: 'full' },
  { path: 'parameters', component: DisplayResultComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
