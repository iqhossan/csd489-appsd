import { Injectable } from '@angular/core';
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';
import { HttpClient } from '@angular/common/http';
import { Dentist } from '../model/dentist';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DentistService {

  private baseUrl = dentistUrl;
  constructor(private _httpClient:HttpClient) { }

  //load all dentist list
  public getAllDentist(page):Observable<Dentist[]>{
    return this._httpClient.get<Dentist[]>(`${this.baseUrl}/dentist/all?pageNumber=${page}`); 
  }

  //load all dentist list without pagination
  public getDentistListExcludePagination():Observable<Dentist[]>{
    return this._httpClient.get<Dentist[]>(`${this.baseUrl}/dentist/`); 
  }
  

  //get dentist by id
  getDentistById(dentistId):Observable<Dentist>{
    return this._httpClient.get<Dentist>(`${this.baseUrl}/dentist/${dentistId}`)
  }

  // save Dentist location
  saveDentist(inputData:Object){ 
    return this._httpClient.post(`${this.baseUrl}/dentist/`,inputData);
  }

  // update Dentist location
  updateDentist(inputData:Object){ 
    return this._httpClient.put(`${this.baseUrl}/dentist/`,inputData);
  }
    //delete the Dentist data
    deleteDentistById(dentistId:number):Observable<Boolean>{ 
      return this._httpClient.delete<Boolean>(`${this.baseUrl}/dentist/${dentistId}`);
    }


}
