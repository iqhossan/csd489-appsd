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
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { BookingComponent } from './pages/booking/booking.component'; 
import { FormsModule } from '@angular/forms';
import { authInterceptorProviders } from './service/auth.interceptor'; 
import { ViewSurgeryComponent } from './pages/surgery/view-surgery/view-surgery.component';
import { AddSurgeryComponent } from './pages/surgery/add-surgery/add-surgery.component';
import { LoaderComponent } from './components/loader/loader.component';
import { EditSurgeryComponent } from './pages/surgery/edit-surgery/edit-surgery.component';
import { DeleteSurgeryComponent } from './pages/surgery/delete-surgery/delete-surgery.component';
import { ViewDentistComponent } from './pages/dentist/view-dentist/view-dentist.component';
import { AddDentistComponent } from './pages/dentist/add-dentist/add-dentist.component';
import { DeleteDentistComponent } from './pages/dentist/delete-dentist/delete-dentist.component';
import { EditDentistComponent } from './pages/dentist/edit-dentist/edit-dentist.component';
import { ViewPatientComponent } from './pages/patient/view-patient/view-patient.component';
import { AddPatientComponent } from './pages/patient/add-patient/add-patient.component';
import { EditPatientComponent } from './pages/patient/edit-patient/edit-patient.component';
import { ViewAppiontmentComponent } from './pages/appointment/view-appiontment/view-appiontment.component';
import { AddAppiontmentComponent } from './pages/appointment/add-appiontment/add-appiontment.component';
import { EditAppiontmentComponent } from './pages/appointment/edit-appiontment/edit-appiontment.component';
import { NgSelectModule } from '@ng-select/ng-select';


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    SignupComponent,
    AdminDashboardComponent, 
    HomeComponent,
    LoginComponent,
    BookingComponent, 
    ViewSurgeryComponent,
    AddSurgeryComponent,
    LoaderComponent,
    EditSurgeryComponent,
    DeleteSurgeryComponent,
    ViewDentistComponent,
    AddDentistComponent,
    DeleteDentistComponent,
    EditDentistComponent,
    ViewPatientComponent,
    AddPatientComponent,
    EditPatientComponent,
    ViewAppiontmentComponent,
    AddAppiontmentComponent,
    EditAppiontmentComponent 
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,  
    NgSelectModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
