import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';
import { Observable } from 'rxjs';
import { Patient } from '../model/patient';


@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private _httpClient:HttpClient) { }

  private baseUrl = patientUrl;

  //load all Patient list
  getAllPatientList(page:number):Observable<Patient[]>{

    return this._httpClient.get<Patient[]>(`${this.baseUrl}/patient/all?pageNumber=${page}`); 
  }

  //get patient by id
  getPatientById(pId:number):Observable<Patient>{
    return this._httpClient.get<Patient>(`${this.baseUrl}/patient/${pId}`)
  }

  // save Patient
  savePatient(inputData:Object){ 
    return this._httpClient.post(`${this.baseUrl}/patient/`,inputData);
  }

  // update Patient
  updatePatient(inputData:Object){ 
    return this._httpClient.put(`${this.baseUrl}/patient/`,inputData);
  }
  
  //delete the Patient data
  deletePatientById(pId:number):Observable<Boolean>{ 
    return this._httpClient.delete<Boolean>(`${this.baseUrl}/patient/${pId}`);
  }

}
