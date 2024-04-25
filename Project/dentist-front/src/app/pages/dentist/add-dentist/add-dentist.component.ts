import { Component, OnInit } from '@angular/core';
import { DentistService } from '../../../service/dentist.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-dentist',
  templateUrl: './add-dentist.component.html',
  styleUrl: './add-dentist.component.css'
})
export class AddDentistComponent implements OnInit {

  firstName!:string;
  lastName!:string;
  phoneNumber!:string;
  email!:string;
  specialization!:string;
  maxAppointment!:number;

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private dentistService:DentistService){}

  ngOnInit(): void {
    
  }

  saveDentist(){

    if(!this.formValidated()){
      return;
    }
    this.isLoading = true;
    this.loadingTitle = 'Saving';
    
    var inputData = {
      firstName: this.firstName,
      lastName: this.lastName,
      phoneNumber: this.phoneNumber,
      email:  this.email,
      specialization: this.specialization
      }

    //console.log(inputData);
    
    this.dentistService.saveDentist(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Dentist Registerd. Add another one.",'success');
        this.clearFields();
        this.isLoading = false;
      },
      error:(err:any)=>{
        this.errors = err.error.errors; 
        console.log(err.error.errors, 'errors');
        Swal.fire('Error','Error in registerting dentist','error');
        this.isLoading = false;
      }
    });
  }


  // clear form field value
  clearFields() {
    this.firstName      = '';
    this.lastName       = '';
    this.phoneNumber    = '';
    this.email          = '';
    this.maxAppointment = null; 
  }

  //data validation
  public formValidated(){
    if(this.firstName == ''||this.firstName == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    } 
    if(this.phoneNumber == ''||this.phoneNumber == null){
      return false;
    }
    return true;
  }

}
