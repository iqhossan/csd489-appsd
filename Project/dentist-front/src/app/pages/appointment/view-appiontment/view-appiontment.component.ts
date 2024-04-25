import { Component } from '@angular/core';
import { Appointment } from '../../../model/appointment';
import { AppointmentService } from '../../../service/appointment.service';
import Swal from 'sweetalert2';

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

  constructor(private appointmentService:AppointmentService){}

  ngOnInit(): void {
    this.getAppointmentList();
  }

  setPage( i, event:any){
    event.preventDefault();
    this.page = i;
    this.getAppointmentList();
  }

  getAppointmentList(){
    this.isLoading = true;
    this.appointmentService.getAllAppointmentWithPatientDentistBranch().subscribe(data=>{
      console.log(data);
      this.appointments = data;
      this.pages=new Array(5);
      this.isLoading = false;
    });
  }

  deleteAppointment(event:any, appointmentId){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.appointmentService.deleteAppointment(appointmentId).subscribe((data:any)=>{
          Swal.fire("Success !!","Dentist Deleted.",'success')
          .then((e)=>{ 
            this.getAppointmentList(); 
          });
      });
    } 
  }

}
