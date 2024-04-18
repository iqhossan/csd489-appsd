import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';

import { Observable } from 'rxjs'; 
import { Appointment } from '../model/appointment';

@Injectable({
  providedIn: 'root'
})

export class AppointmentService {

  private baseUrl = appointmentUrl;


  constructor(private _http:HttpClient) { }

   // load all apointments 
   public getAllAppointment(){
    return this ._http.get(`${this.baseUrl}/`);
  }

   // load all apointments by appointment id 
   public getAppointmentById(appointmentId){
    return this ._http.get(`${this.baseUrl}/${appointmentId}`);
  }

  // add new apointment
  public createAppointment(Appointment){
    return this._http.post(`${this.baseUrl}/`,Appointment)
  }

  // update apointment
  public updateAppointment(Appointment){
    return this._http.put(`${this.baseUrl}/`,Appointment)
  }
  
  //delete apointment
  public deleteAppointment(appointmentId){
    return this._http.delete(`${this.baseUrl}/${appointmentId}`);
  }

  // load all apointments along with doctors and pateint information
  public getAllAppointmentWithPatientDentist():Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/doctor/patient/list`);
  }
  
}
