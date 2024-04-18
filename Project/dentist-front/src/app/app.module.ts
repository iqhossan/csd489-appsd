import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { SignupComponent } from './pages/signup/signup.component'; 
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component'; 
import { HttpClientModule } from '@angular/common/http';
import { AppointmentComponent } from './pages/appointment/appointment.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { BookingComponent } from './pages/booking/booking.component'; 
import { FormsModule } from '@angular/forms';
import { authInterceptorProviders } from './service/auth.interceptor';
import { PatientComponent } from './pages/patient/patient.component';
import { ViewSurgeryComponent } from './pages/surgery/view-surgery/view-surgery.component';
import { AddSurgeryComponent } from './pages/surgery/add-surgery/add-surgery.component';
import { LoaderComponent } from './components/loader/loader.component';
import { EditSurgeryComponent } from './pages/surgery/edit-surgery/edit-surgery.component';
import { DeleteSurgeryComponent } from './pages/surgery/delete-surgery/delete-surgery.component';
 

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    AdminDashboardComponent,
    AppointmentComponent,
    HomeComponent,
    LoginComponent,
    BookingComponent,
    PatientComponent,
    ViewSurgeryComponent,
    AddSurgeryComponent,
    LoaderComponent,
    EditSurgeryComponent,
    DeleteSurgeryComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
