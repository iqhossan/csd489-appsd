import { Component, OnInit } from '@angular/core';
import { Address } from '../../../model/address';
import { PatientService } from '../../../service/patient.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-patient',
  templateUrl: './add-patient.component.html',
  styleUrl: './add-patient.component.css'
})
export class AddPatientComponent implements OnInit {

  patientId!:number;
  firstName!:string;
  lastName!:string;
  phoneNo!:string;
  email!:string;
  dues:number = 0.0; 
  street1!: String;
  street2!: String;
  state!: String;
  city!: String;
  zipcode!: number; 
  address!: Address; 

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private patientService:PatientService){}

  ngOnInit(): void {
    
  }

  savePatient(){

    if(!this.formValidated()){
      return;
    }
    this.isLoading = true;
    this.loadingTitle = 'Saving';
    var inputData = {
      //patientId:this.patientId,
      firstName:this.firstName,
      lastName:this.lastName,
      phoneNo:this.phoneNo,
      email:this.email,
      address: {
          street1: this.street1,
          street2: this.street2,
          state: this.state,
          city: this.city,
          zipcode: this.zipcode,
        }
      }

    console.log(inputData);
    
    this.patientService.savePatient(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Patient has been enrolled. Add another one.",'success');
        this.clearFields();
        this.isLoading = false;
      },
      error:(err:any)=>{
        this.errors = err.error.errors; 
        console.log(err.error.errors, 'errors');
        Swal.fire('Error','Error in Patient Enrollment','error');
        this.isLoading = false;
      }
    });
  }


  // clear form field value
  clearFields() {
    this.firstName  = '';
    this.lastName   = '';
    this.phoneNo    = '';
    this.email      = ''; 
    this.street1    = '';
    this.street2    = '';
    this.state      = '';
    this.city       = ''; 
    this.zipcode    = null;
    this.address    = null;  
  }

  //data validation
  public formValidated(){
    if(this.firstName == ''||this.lastName == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.phoneNo == '' || this.phoneNo == null){
      return false;
    }
    if(this.street1 == '' || this.street1 == null){
      return false;
    }
    if(this.state == '' || this.state == null){
      return false;
    }
    if(this.city == '' || this.city == null){
      return false;
    }
    if(this.zipcode == null){
      return false;
    }
    return true;
  }


}
