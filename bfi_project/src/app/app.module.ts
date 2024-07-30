import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms'; // Import ReactiveFormsModule 
import { HttpClientModule } from '@angular/common/http';
import { ContentComponent } from './content/content.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MetricServicesComponent } from './metric-services/metric-services.component';
import { ParameterSelectorComponent } from './parameter-selector/parameter-selector.component';
import { ParameterDisplayComponent } from './parameter-display/parameter-display.component';  

@NgModule({
  declarations: [
    AppComponent,
    ContentComponent,
    LoginFormComponent,
    NavbarComponent,
    MetricServicesComponent,
    ParameterSelectorComponent,
    ParameterDisplayComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule, // Add ReactiveFormsModule here

    HttpClientModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
