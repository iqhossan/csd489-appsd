import { Injectable } from '@angular/core';

import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = userUrl;
  constructor(private _httpClient:HttpClient) { }

   //load all user list
   public getAllUserList():Observable<User[]>{
    return this._httpClient.get<User[]>(`${this.baseUrl}/user/`); 
  }

  //load user by user name list
  public getUser(username):Observable<User[]>{
    return this._httpClient.get<User[]>(`${this.baseUrl}/user/${username}`); 
  }

  // save User
  saveUser(inputData:Object){ 
    return this._httpClient.post(`${this.baseUrl}/user/register`,inputData);
  }

   //delete the User data
   deleteUserById(uId:number):Observable<Boolean>{ 
    return this._httpClient.delete<Boolean>(`${this.baseUrl}/user/${uId}`);
  }



}
