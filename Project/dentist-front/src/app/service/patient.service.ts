import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';
import { Observable } from 'rxjs';
import { Patient } from '../model/patient';


@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private httpClient:HttpClient) { }

  private baseUrl = patientUrl;


  getPatientList():Observable<Patient[]>{
    console.log(`${this.baseUrl}/`);
    return this.httpClient.get<Patient[]>(`${this.baseUrl}/patient/`)
  }

  // public quizzes(){
  //   return this.httpClient.get(`${baseUrl}/quiz/`)
  // }

}
