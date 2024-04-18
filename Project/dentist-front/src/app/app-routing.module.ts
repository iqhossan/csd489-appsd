import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppointmentComponent } from './pages/appointment/appointment.component';
import { AdminDashboardComponent } from './pages/admin-dashboard/admin-dashboard.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { BookingComponent } from './pages/booking/booking.component'; 
import { ViewSurgeryComponent } from './pages/surgery/view-surgery/view-surgery.component';
import { AddSurgeryComponent } from './pages/surgery/add-surgery/add-surgery.component';
import { DeleteSurgeryComponent } from './pages/surgery/delete-surgery/delete-surgery.component';
import { EditSurgeryComponent } from './pages/surgery/edit-surgery/edit-surgery.component';

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
    path:'surgery',
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
  {
    path:'delete-surgery',
    component:DeleteSurgeryComponent,
    pathMatch:'full',
  }, 
  {
    path:'admin',
    component:AdminDashboardComponent,
    children:[
      {
        path:'appointment',
        component:AppointmentComponent,
      },
    ],
  },
  {
    path:'appoint',
    component:AppointmentComponent,
  },
   
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
