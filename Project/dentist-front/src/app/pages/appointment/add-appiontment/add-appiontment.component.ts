import { Component } from '@angular/core';
import { Address } from '../../../model/address';
import { AppointmentService } from '../../../service/appointment.service';
import { Time } from '@angular/common';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../../../service/patient.service';
import { Patient } from '../../../model/patient';
import { Dentist } from '../../../model/dentist';
import { DentistService } from '../../../service/dentist.service'; 
import { SurgeryService } from '../../../service/surgery.service';
import { Surgery } from '../../../model/surgery';

@Component({
  selector: 'app-add-appiontment',
  templateUrl: './add-appiontment.component.html',
  styleUrl: './add-appiontment.component.css'
})
export class AddAppiontmentComponent {


  appointmentDate!:Date;
  appointmentTime!:Time;
  dentistId!:number;
  patientId!:number;
  branchId!:number;
  
  patients:any;
  dentists:Dentist;
  surgeris:Surgery;

  selectedDentist:number;
  selectedLocation:number;

  errors:any = [];
 
  selectedItem: any;
  isLoading:Boolean = false;
  loadingTitle:String = '';
 
  constructor(private appointmentService:AppointmentService,
    private patientService:PatientService,
    private dentistService:DentistService,
    private surgeryService:SurgeryService,
    private _route:ActivatedRoute,
    private _router:Router){
      this.selectedDentist = this.dentistId;
      this.selectedLocation = this.branchId;
    }

  loadingDentistData(){
    this.dentistService.getDentistListExcludePagination().subscribe(
      (data:any)=>{ 
        this.dentists = data;
        //console.log(this.dentists);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!',"Error in loading data from server",'error');
      }
    );
  }

  loadingSurgeryLocationData(){
    this.surgeryService.getBranchExcludePagination().subscribe(
      (data:any)=>{ 
        this.surgeris = data;
        //console.log(this.surgeris);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!',"Error in loading data from server",'error');
      }
    );
  }

  loadingPatientData(){
    this.patientService.getPatientById(this.patientId).subscribe(data=>{
      //console.log(data);
      this.patients = data; 
    });
  }

  ngOnInit(): void { 

    //Loadig Patient data
    this.patientId = parseInt(this._route.snapshot.paramMap.get('pid'));  
    this.loadingPatientData();

    //loading dentist data
    this.loadingDentistData();

    //loading Surgery Location data
    this.loadingSurgeryLocationData();
  }

  itemSelected(e:any){
    console.log(e);
  }

  saveAppointment(){
    if(!this.formValidated()){
      return;
    }
    this.isLoading = true;
    this.loadingTitle = 'Saving';

    var inputData = { 
      appointmentDate:this.appointmentDate,
      appointmentTime:this.appointmentTime, 
      dentistId:this.selectedDentist,
      patientId:this.patientId,
      branchId:this.selectedLocation
    }

    console.log(inputData);
    
    this.appointmentService.createAppointment(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Appointment has been done.",'success');
        this.clearFields();
        this.loadingPatientData();
        //loading dentist data
        this.loadingDentistData();
        //loading Surgery Location data
        this.loadingSurgeryLocationData();
        this.isLoading = false;
      },
      error:(err:any)=>{
        this.errors = err.error.errors; 
        console.log(err.error.errors, 'errors');
        Swal.fire('Error','Error in Appointment','error');
        this.isLoading = false;
      }
    });
  }


  // clear form field value
  clearFields() {
    this.appointmentDate  = null;
    this.appointmentTime  = null;
    this.dentistId    = null;  
    this.branchId    = null;
  }

  //data validation
  public formValidated(){
    if(this.appointmentDate == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.appointmentTime == null){
      return false;
    }
    // if(this.dentistId == null || this.dentistId == -1){
    //   return false;
    // } 
    return true;
  }

}
