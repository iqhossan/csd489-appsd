import { Component } from '@angular/core';
import { AppointmentService } from '../../../service/appointment.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { PatientService } from '../../../service/patient.service';
import { DentistService } from '../../../service/dentist.service';
import { SurgeryService } from '../../../service/surgery.service';
import { Dentist } from '../../../model/dentist';
import { Surgery } from '../../../model/surgery';

@Component({
  selector: 'app-edit-appiontment',
  templateUrl: './edit-appiontment.component.html',
  styleUrl: './edit-appiontment.component.css'
})
export class EditAppiontmentComponent {

  appointments:any;
  appointmentId!:any;
  patientId!:number;

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
    private _router:Router){}

  ngOnInit(): void {

    this.appointmentId = this._route.snapshot.paramMap.get('appointid'); 
    this.isLoading = true;
    this.appointmentService.getAppointmentById(this.appointmentId).subscribe(data=>{
      console.log(data);
      this.appointments = data;
      this.patientId = parseInt( this.appointments.patientId);

      this.selectedDentist = this.appointments.dentistId;
      this.selectedLocation = this.appointments.branchId;

      //Loadig Patient data  
      this.loadingPatientData();

      //loading dentist data
      this.loadingDentistData();

      //loading Surgery Location data
      this.loadingSurgeryLocationData();

      this.isLoading = false;
    });
   
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

  updateAppointment(){

    if(!this.formValidated()){
      return;
    }

    this.isLoading = true;
    this.loadingTitle = 'Updating';
    
    var inputData = {
      appointmentDate:this.appointments.appointmentDate,
      appointmentTime:this.appointments.appointmentTime, 
      dentistId:this.selectedDentist,
      patientId:this.appointments.patientId,
      branchId:this.selectedLocation
      }

    //console.log(inputData,'response');
    
    this.appointmentService.updateAppointment(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Appointment Booking Updated.",'success')
        .then((e)=>{
          this.isLoading = false;
          this._router.navigate(['/view-appointment']);
        });
      },
      error:(err:any)=>{ 
        console.log(err, 'errors');
        Swal.fire('Error','Error in updating appointment booking','error');
        this.isLoading = false;
      }
    });
  }

   //data validation
   public formValidated(){
    if(this.appointments.appointmentDate == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.appointments.appointmentTime == null){
      return false;
    }
    
    return true;
  }
}
