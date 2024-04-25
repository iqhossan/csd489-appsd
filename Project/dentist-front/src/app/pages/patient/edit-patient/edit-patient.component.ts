import { Component, OnInit } from '@angular/core'; 
import { PatientService } from '../../../service/patient.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-patient',
  templateUrl: './edit-patient.component.html',
  styleUrl: './edit-patient.component.css'
})
export class EditPatientComponent implements OnInit {

  patients:any;
  patientId!:any;
  addressId!:any;

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private patientService:PatientService,
    private _route:ActivatedRoute,
    private _router:Router){}

  ngOnInit(): void {
    this.patientId = this._route.snapshot.paramMap.get('pid');
    this.addressId = this._route.snapshot.paramMap.get('addid');
    this.isLoading = true;
    this.patientService.getPatientById(this.patientId).subscribe(data=>{
      //console.log(data);
      this.patients = data;
      this.isLoading = false;
    });
   
  }

  updatePatient(){

    if(!this.formValidated()){
      return;
    }

    this.isLoading = true;
    this.loadingTitle = 'Updating';
    
    var inputData = {
      patientId: this.patients.patientId,
      firstName: this.patients.firstName,
      lastName: this.patients.lastName,
      phoneNo: this.patients.phoneNo, 
      email: this.patients.email, 
      address: {
          addressId:this.patients.address.addressId,
          street1: this.patients.address.street1,
          street2: this.patients.address.street2,
          state: this.patients.address.state,
          city: this.patients.address.city,
          zipcode: this.patients.address.zipcode,
        }
      }

    //console.log(inputData,'response');
    
    this.patientService.updatePatient(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Patient Enrollment Updated.",'success')
        .then((e)=>{
          this.isLoading = false;
          this._router.navigate(['/view-patient']);
        });
      },
      error:(err:any)=>{ 
        console.log(err, 'errors');
        Swal.fire('Error','Error in updating patient enrollment','error');
        this.isLoading = false;
      }
    });
  }

  public formValidated(){
    if(this.patients.firstName == ''||this.patients.firstName == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.patients.phoneNo == ''||this.patients.phoneNo == null){
      return false;
    }
    if(this.patients.address.street1 == ''||this.patients.address.street1 == null){
      return false;
    }
    if(this.patients.address.state == ''||this.patients.address.state == null){
      return false;
    }
    if(this.patients.address.city == ''||this.patients.address.city == null){
      return false;
    }
    if(this.patients.address.zipcode == null){
      return false;
    }
    return true;
  }
 
}
