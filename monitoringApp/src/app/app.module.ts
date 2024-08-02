import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginFormComponent } from './log_auth/login-form/login-form.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ContentComponent } from './log_auth/content/content.component';
import { DisplayResultComponent } from './Metrics/display-result/display-result.component';
import { UserPickUpServiceComponent } from './Metrics/user-pick-up-service/user-pick-up-service.component';
import { LinkComponent } from './Metrics/link/link.component';
import { NavigationbarComponent } from './Layout-Component/navigationbar/navigationbar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginFormComponent,
    ContentComponent,
    DisplayResultComponent,
    UserPickUpServiceComponent,
    LinkComponent,
    NavigationbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule, // Add ReactiveFormsModule here
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
