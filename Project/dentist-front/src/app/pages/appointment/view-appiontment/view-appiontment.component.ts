import { Component } from '@angular/core';
import { Appointment } from '../../../model/appointment';
import { AppointmentService } from '../../../service/appointment.service';
import Swal from 'sweetalert2';
import { LoginService } from '../../../service/login.service';

@Component({
  selector: 'app-view-appiontment',
  templateUrl: './view-appiontment.component.html',
  styleUrl: './view-appiontment.component.css'
})
export class ViewAppiontmentComponent {

  private page:number = 0;
  private pages:Array<number>
  private appointments:Appointment[];

  isLoading:Boolean = false;
  whomid = 0;
  role=null;
  constructor(private appointmentService:AppointmentService,
    private login:LoginService){}

  ngOnInit(): void { 
    this.whomid = this.login.getUserWhomId();
    this.role = this.login.getUserRole(); 
   // alert(this.role);
    this.getAppointmentList(this.role);
  }

  setPage( i, event:any){
    event.preventDefault();
    this.page = i;
    this.getAppointmentList(this.role);
  }

  getAppointmentList(rr:any){
    this.isLoading = true;
    
    if(rr === "PATIENT"){
      this.appointmentService.getAllPatientAppointment(this.whomid).subscribe(data=>{
       // console.log(data);
        this.appointments = data;
        this.pages=new Array(5);
        this.isLoading = false;
      });
    }else if(rr === "DENTIST"){
      this.appointmentService.getAllDoctorAppointment(this.whomid).subscribe(data=>{
       // console.log(data);
        this.appointments = data;
        this.pages=new Array(5);
        this.isLoading = false;
      });
    }else{
      this.appointmentService.getAllAppointmentExceludePage().subscribe(data=>{
       // console.log(data);
        this.appointments = data;
        this.pages=new Array(5);
        this.isLoading = false;
      });
    }
    
  }

  deleteAppointment(event:any, appointmentId){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.appointmentService.deleteAppointment(appointmentId).subscribe((data:any)=>{
          Swal.fire("Success !!","Dentist Deleted.",'success')
          .then((e)=>{ 
            this.getAppointmentList(this.role); 
          });
      });
    } 
  }

}
