import { Component, OnInit } from '@angular/core';
import { PatientService } from '../../service/patient.service';
import { Patient } from '../../model/patient';

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrl: './admin-dashboard.component.css'
})
export class AdminDashboardComponent implements OnInit {

  
  constructor(){}

  ngOnInit():void{
    
  }

  
}
