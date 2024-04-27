import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router'; 
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { BookingComponent } from './pages/booking/booking.component'; 
import { ViewSurgeryComponent } from './pages/surgery/view-surgery/view-surgery.component';
import { AddSurgeryComponent } from './pages/surgery/add-surgery/add-surgery.component';
import { DeleteSurgeryComponent } from './pages/surgery/delete-surgery/delete-surgery.component';
import { EditSurgeryComponent } from './pages/surgery/edit-surgery/edit-surgery.component';
import { ViewDentistComponent } from './pages/dentist/view-dentist/view-dentist.component';
import { AddDentistComponent } from './pages/dentist/add-dentist/add-dentist.component';
import { EditDentistComponent } from './pages/dentist/edit-dentist/edit-dentist.component';
import { DeleteDentistComponent } from './pages/dentist/delete-dentist/delete-dentist.component';
import { ViewPatientComponent } from './pages/patient/view-patient/view-patient.component';
import { AddPatientComponent } from './pages/patient/add-patient/add-patient.component';
import { EditPatientComponent } from './pages/patient/edit-patient/edit-patient.component';
import { AddAppiontmentComponent } from './pages/appointment/add-appiontment/add-appiontment.component';
import { EditAppiontmentComponent } from './pages/appointment/edit-appiontment/edit-appiontment.component';
import { ViewAppiontmentComponent } from './pages/appointment/view-appiontment/view-appiontment.component';
import { ViewUserComponent } from './pages/user/view-user/view-user.component';
import { AddUserComponent } from './pages/user/add-user/add-user.component';
import { PatientDashboardComponent } from './pages/patient-dashboard/patient-dashboard.component';
import { DentistDashboardComponent } from './pages/dentist-dashboard/dentist-dashboard.component';

const routes: Routes = [
  {
    path:'home',
    component:HomeComponent,
    pathMatch:'full',
  },
  {
    path:'',
    redirectTo:'home', 
    pathMatch:'full',
  },
  {
    path:'login',
    component:LoginComponent,
    pathMatch:'full',
  },
  {
    path:'booking',
    component:BookingComponent,
    pathMatch:'full',
  },
  
  {
    path:'admin',
    component:AdminDashboardComponent,
    children:[
      {
        path:'appointment',
        component:ViewAppiontmentComponent,
      },
    ],
  },

  {
    path:'patient',
    component:PatientDashboardComponent,
    pathMatch:'full',
  },

  {
    path:'dentist', 
    component:DentistDashboardComponent,
    pathMatch:'full',
  },

  // start surgery location
  {
    path:'view-surgery',
    component:ViewSurgeryComponent,
    pathMatch:'full',
  },
  {
    path:'add-surgery',
    component:AddSurgeryComponent,
    pathMatch:'full',
  }, 
  {
    path:'edit-surgery/:brid/:addid',
    component:EditSurgeryComponent,
    title:"Edit Surgery Location",
    pathMatch:'full',
  }, 

  // Start dentist
  {
    path:'view-dentist',
    component: ViewDentistComponent,
    pathMatch:'full',
  },

  {
    path:'add-dentist',
    component:AddDentistComponent,
    pathMatch:'full',
  }, 
  {
    path:'edit-dentist/:dentid',
    component:EditDentistComponent,
    title:"Edit Dentist",
    pathMatch:'full',
  }, 
  
  // Start Patient
  {
    path:'view-patient',
    component: ViewPatientComponent,
    pathMatch:'full',
  },
  {
    path:'add-patient',
    component:AddPatientComponent,
    pathMatch:'full',
  }, 
  {
    path:'edit-patient/:pid/:addid',
    component:EditPatientComponent,
    title:"Edit Patient",
    pathMatch:'full',
  }, 

  // end patinet
  //Start appointment
  {
    path:'appoint',
    component:ViewAppiontmentComponent,
  },
  
  {
    path:'add-appointment/:pid',
    component:AddAppiontmentComponent,
    pathMatch:'full',
  },

  {
    path:'edit-appointment/:appointid',
    component:EditAppiontmentComponent,
    pathMatch:'full',
  },

  {
    path:'view-appointment',
    component:ViewAppiontmentComponent,
    pathMatch:'full',
  },

  // Start user-------------

  {
    path:'view-user',
    component:ViewUserComponent,
    pathMatch:'full',
  },

  {
    path:'add-user',
    component:AddUserComponent,
    pathMatch:'full',
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
