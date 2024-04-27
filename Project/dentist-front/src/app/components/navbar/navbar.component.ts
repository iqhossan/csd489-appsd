import { Component } from '@angular/core';
import { LoginService } from '../../service/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isLoggedIn = false;
  whomid = 0;
  user = null;
  role = null;
  constructor(public login:LoginService,
    private router:Router){}
    
  ngOnInit(): void {
    this.isLoggedIn = this.login.isLoggedIn();
    this.user = this.login.getUser(); 
    this.role = this.login.getUserRole();
    this.whomid = this.login.getUserWhomId();
    this.login.loginStatusSubject.asObservable().subscribe((data)=>{
      this.isLoggedIn = this.login.isLoggedIn();
      this.user = this.login.getUser();
      this.role = this.login.getUserRole();
      this.whomid = this.login.getUserWhomId();
      //alert(this.whomid);
      //console.log(this.user);
    });
  }

  public logout(){
    this.login.logout(); 
    this.router.navigate(['/login'])
    this.login.loginStatusSubject.next(false);
    //window.location.reload();
  }
}
