import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../model/appointment';
import { AppointmentService } from '../../service/appointment.service';

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.css'
})
export class AppointmentComponent implements OnInit {

  appointments:Appointment[]=[];
  constructor(private appointmentService:AppointmentService){}

  ngOnInit(): void {
    this.getAppointmentWithPatientDoctor();
  }

  getAppointmentWithPatientDoctor(){
    this.appointmentService.getAllAppointmentWithPatientDentist().subscribe(data=>{
      this.appointments = data;
    });
  }

}
