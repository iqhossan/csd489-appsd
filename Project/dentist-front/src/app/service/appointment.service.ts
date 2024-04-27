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
   public getAllAppointment(page){
    return this ._http.get(`${this.baseUrl}/appointment/all?pageNumber=${page}`);
  }

  // load all apointments 
  public getAppointmentExcludePagination(){
    return this ._http.get(`${this.baseUrl}/appointment/`);
  }


   // load all apointments by appointment id 
   public getAppointmentById(appointmentId){
    return this ._http.get(`${this.baseUrl}/appointment/${appointmentId}`);
  }

  // add new apointment
  public createAppointment(Appointment){
    return this._http.post(`${this.baseUrl}/appointment/`,Appointment)
  }

  // update apointment
  public updateAppointment(Appointment){
    return this._http.put(`${this.baseUrl}/appointment/`,Appointment)
  }
  
  //delete apointment
  public deleteAppointment(appointmentId){
    return this._http.delete(`${this.baseUrl}/appointment/${appointmentId}`);
  }

  // load all apointments along with doctors and pateint information
  public getAllAppointmentWithPatientDentist():Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/doctor/patient/list`);
  }

   // load all apointments along with doctors , pateint and Branch information
   public aagetAllAppointmentWithPatientDentistBranch():Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/doctor/patient/branch/list`);
  }
  
   // load all apointments information
   public getAllAppointmentExceludePage():Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/list`);
  }

  // load all apointments of doctors information
  public getAllDoctorAppointment(dentistId:number):Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/list/dentist/${dentistId}`);
  }

  // load all apointments of patient information
  public getAllPatientAppointment(patientId:number):Observable<Appointment[]>{
    return this._http.get<Appointment[]>(`${this.baseUrl}/appointment/list/patient/${patientId}`);
  }
}
