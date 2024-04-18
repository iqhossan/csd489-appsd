import { Component, OnInit } from '@angular/core';
import { Address } from '../../../model/address';
import { SurgeryService } from '../../../service/surgery.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-surgery',
  templateUrl: './add-surgery.component.html',
  styleUrl: './add-surgery.component.css'
})
export class AddSurgeryComponent implements OnInit {

  branchname!: String;
  telephoneno!: String;
  street1!: String;
  street2!: String;
  state!: String;
  city!: String;
  zipcode!: number; 
  address!: Address; 

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private surgeryService:SurgeryService){}

  ngOnInit(): void {
    
  }

  saveLocation(){

    if(!this.formValidated()){
      return;
    }
    this.isLoading = true;
    this.loadingTitle = 'Saving';
    
    var inputData = {
      branchName: this.branchname,
      telephoneNo: this.telephoneno,
      companyId: 1,
      address: {
          street1: this.street1,
          street2: this.street2,
          state: this.state,
          city: this.city,
          zipcode: this.zipcode,
        }
      }

    //console.log(inputData);
    
    this.surgeryService.saveLocation(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Location Added. Add another one.",'success');
        this.clearFields();
        this.isLoading = false;
      },
      error:(err:any)=>{
        this.errors = err.error.errors; 
        console.log(err.error.errors, 'errors');
        Swal.fire('Error','Error in adding location','error');
        this.isLoading = false;
      }
    });
  }


  // clear form field value
  clearFields() {
    this.branchname   = '';
    this.telephoneno  = '';
    this.street1      = '';
    this.street2      = '';
    this.state        = '';
    this.city         = ''; 
    this.zipcode      = null;
    this.address      = null;  
  }

  //data validation
  public formValidated(){
    if(this.branchname == ''||this.branchname == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.telephoneno == ''||this.telephoneno == null){
      return false;
    }
    if(this.street1 == ''||this.street1 == null){
      return false;
    }
    if(this.state == ''||this.state == null){
      return false;
    }
    if(this.city == ''||this.city == null){
      return false;
    }
    if(this.zipcode == null){
      return false;
    }
    return true;
  }

}
