import { Component, OnInit } from '@angular/core';
import { Patient } from '../../../model/patient';
import { PatientService } from '../../../service/patient.service';
import Swal from 'sweetalert2';
import { LoginService } from '../../../service/login.service';

@Component({
  selector: 'app-view-patient',
  templateUrl: './view-patient.component.html',
  styleUrl: './view-patient.component.css'
})
export class ViewPatientComponent implements OnInit {

  private page:number = 0;
  private pages:Array<number>
  private patientdata:Patient[];

  isLoading:Boolean = false;
  role=null;
  constructor(private patientService:PatientService,
    private login:LoginService
  ){}

  ngOnInit(): void {
    this.getPatientList();
    this.role = this.login.getUserRole();
  }

  setPage( i, event:any){
    event.preventDefault();
    this.page = i;
    this.getPatientList();
  }

  getPatientList(){
    this.isLoading = true;
    this.patientService.getAllPatientList(this.page).subscribe(data=>{
      this.patientdata = data;
      this.pages=new Array(5);
      this.isLoading = false;
    });
  }

  deletePatient(event:any, pid){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.patientService.deletePatientById(pid).subscribe((data:any)=>{
          Swal.fire("Success !!","Patient Deleted.",'success')
          .then((e)=>{ 
            this.getPatientList(); 
          });
      });
    } 
  }

}
