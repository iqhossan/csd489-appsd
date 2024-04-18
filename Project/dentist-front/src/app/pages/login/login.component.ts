import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Router } from '@angular/router';
 
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit {

  loginData = {
    username:'',
    password:''
  }

  constructor(private loginService:LoginService,
    private router:Router){};

  ngOnInit(): void {
    
  }

  formSubmit(){
    this.loginService.generateToken(this.loginData).subscribe(
      (data:any)=>{
        console.log('success');
        console.log(data);
        this.loginService.loginUser(data.token); // set token in local storage
        this.loginService.getCurrentUser().subscribe(
          (user:any)=>{
            this.loginService.setUser(user);
             console.log(user); 
             if(this.loginService.getUserRole() == 'ADMIN'){
              //ridirect  ...ADMIN dashboard
              //window.location.href="/admin";
              this.router.navigate(['admin']);
              this.loginService.loginStatusSubject.next(true);
            }else if(this.loginService.getUserRole() == 'PATIENT'){
              //ridirect ...NORMAL dashboard
              //window.location.href="/user-dashboard";
              this.router.navigate(['user-dashboard/0']);
              this.loginService.loginStatusSubject.next(true);
            }else if(this.loginService.getUserRole() == 'DOCTOR'){
              //ridirect ...NORMAL dashboard 
              this.router.navigate(['user-dashboard/0']);
              this.loginService.loginStatusSubject.next(true);
            }else{
              this.loginService.logout();
            } 
          });

      },
      (error)=>{
        console.log('error');
        console.log(error);
      }
    );
  }

}
