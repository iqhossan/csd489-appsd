import { Injectable } from '@angular/core';
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Surgery } from '../model/surgery';


@Injectable({
  providedIn: 'root'
})
export class SurgeryService {

  private baseUrl = managerUrl;
  constructor(private httpClient:HttpClient) { }

  //Load all surgery location
  getAllBranch(page):Observable<Surgery[]>{ 
    //console.log(`${this.baseUrl}/branch/all?pageNumber=${page}&pageSize=3`); 
    return this.httpClient.get<Surgery[]>(`${this.baseUrl}/branch/all?pageNumber=${page}`);
  }

   //Load all surgery location
   getBranchExcludePagination():Observable<Surgery[]>{ 
    //console.log(`${this.baseUrl}/branch/all?pageNumber=${page}&pageSize=3`); 
    return this.httpClient.get<Surgery[]>(`${this.baseUrl}/branch/`);
  }


  // save surgery location
  saveLocation(inputData:Object){ 
    return this.httpClient.post(`${this.baseUrl}/branch/`,inputData);
  }
  
   // save surgery location
   updateLocation(inputData:Object){ 
    return this.httpClient.put(`${this.baseUrl}/branch/`,inputData);
  }
  
  // get specific branch location by branch id
  getBranchDataById(branchId):Observable<Surgery>{ 
    return this.httpClient.get<Surgery>(`${this.baseUrl}/branch/${branchId}`);
  }

  

  //delete the branch data
  deleteBranchDataById(branchId:number):Observable<Boolean>{ 
    return this.httpClient.delete<Boolean>(`${this.baseUrl}/branch/${branchId}`);
  }
  
}
