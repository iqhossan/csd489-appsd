import { Component, OnInit } from '@angular/core';
import { Patient } from '../../model/patient';
import { PatientService } from '../../service/patient.service';

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrl: './patient.component.css'
})
export class PatientComponent implements OnInit {

  patients:Patient[]=[];
  constructor(private patientSerive:PatientService){}

  ngOnInit():void{
    this.getPatients();
  }

  getPatients(){
    this.patientSerive.getPatientList().subscribe(data=>{
      this.patients=data;
    });
  }

}
