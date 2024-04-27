import { Component, OnInit } from '@angular/core';
import { DentistService } from '../../../service/dentist.service';
import { Dentist } from '../../../model/dentist';
import Swal from 'sweetalert2';
import { LoginService } from '../../../service/login.service';

@Component({
  selector: 'app-view-dentist',
  templateUrl: './view-dentist.component.html',
  styleUrl: './view-dentist.component.css'
})
export class ViewDentistComponent implements OnInit {

  private page:number = 0;
  private pages:Array<number>
  private dentistdata:Dentist[];

  isLoading:Boolean = false;
  role = null;
  constructor(private dentistService:DentistService,
    private login:LoginService
  ){}

  ngOnInit(): void {
    this.getDentistList();
    this.role = this.login.getUserRole();
  }

  setPage( i, event:any){
    event.preventDefault();
    this.page = i;
    this.getDentistList();
  }

  getDentistList(){
    this.isLoading = true;
    this.dentistService.getAllDentist(this.page).subscribe(data=>{
      this.dentistdata = data;
      this.pages=new Array(5);
      this.isLoading = false;
    });
  }

  deleteDentist(event:any, dentistid){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.dentistService.deleteDentistById(dentistid).subscribe((data:any)=>{
          Swal.fire("Success !!","Dentist Deleted.",'success')
          .then((e)=>{ 
            this.getDentistList(); 
          });
      });
    } 
  }

}
