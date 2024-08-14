import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DisplayResultComponent } from './Metrics/display-result/display-result.component';
import { LinkComponent } from './Metrics/link/link.component';
import { ContentComponent } from './log_auth/content/content.component';

const routes: Routes = [
  { path: '', component: ContentComponent }, // Display ContentComponent at the root URL
   { path: 'services', component: LinkComponent },
  { path: 'about',   },
  { path: 'parameters', component: DisplayResultComponent },
  { path: '**', redirectTo: '' } // Redirect all undefined paths to root
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
