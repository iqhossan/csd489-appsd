import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs'; 
import { userUrl, dentistUrl, patientUrl, addressUrl, appointmentUrl, billingUrl, managerUrl } from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>(); 
  
  constructor(private http:HttpClient) { }

  private baseurl = userUrl;

  //current user: who is loogedIn
  public getCurrentUser(){
    return this.http.get(`${this.baseurl}/auth/current-user`)
  }
  
  //generate token
  public generateToken(loginData:any){ 
    return this.http.post(`${this.baseurl}/auth/generate-token`,loginData)
  }

  //login user: set token in local storage
  public loginUser(token){
    localStorage.setItem('token',token);
    return true;
  }

  //isLoggedIn: user is login or not
  public isLoggedIn(){
    let tokenStr = localStorage.getItem('token');
    if(tokenStr == undefined || tokenStr == '' || tokenStr == null){
      return false;
    }else{
      return true;
    }
  }

  //logout: remove token from localStorage
  public logout(){
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    return true;
  }

  //get token from local storage
  public getToken(){
    return localStorage.getItem('token');
  }

  //set user details in localstorage
  public setUser(user){
    localStorage.setItem('user',JSON.stringify(user));
  }

  //getUser information
  public getUser(){
    let userStr = localStorage.getItem('user');
    if(userStr != null){
      return JSON.parse(userStr);
    }else{
      this.logout();
      return null;
    }
  }

  //get user role
  public getUserRole(){
    let user = this.getUser();
    return user.authorities[0].authority;
  }

   //get user id
   public getUserWhomId(){
    let user = this.getUser();
    return user.whomId;
  }

}
