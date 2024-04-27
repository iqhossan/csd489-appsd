import { Component, OnInit } from '@angular/core';
import { UserService } from '../../../service/user.service';
import Swal from 'sweetalert2';
import { Role } from '../../../model/role';
import { Dentist } from '../../../model/dentist';
import { PatientService } from '../../../service/patient.service';
import { DentistService } from '../../../service/dentist.service';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrl: './add-user.component.css'
})
export class AddUserComponent implements OnInit {

  firstname!:string;
  lastname!:string;
  username!:string;
  password!:string;
  email!:string;
  roleId!:number; 
  whomId!:number;

  patientId!:number; 
  dentistId!:number; 

  patients:any;
  dentists:any;

  roles: Role[] = [
    { roleId: 45, name: 'ADMIN' },
    { roleId: 44, name: 'PATIENT' },
    { roleId: 43, name: 'DENTIST' },
    // Add more roles as needed
  ];

  selectedRole:number;
  selectedPatient:number; 
  selectedDentist:number;

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private userService:UserService,
    private patientService:PatientService,
    private dentistService:DentistService){
    this.selectedRole = this.roleId;
    this.selectedPatient = this.patientId; 
    this.selectedDentist = this.dentists;
  }

  ngOnInit(): void {
   this.loadingDentistData();
   this.loadingPatientData();
  }

  itemSelected(e:any){
    console.log(e);
  }

  saveUser(){

    if(!this.formValidated()){
      return;
    }
    this.isLoading = true;
    this.loadingTitle = 'Saving';
    //alert(this.selectedPatient + "::"+this.selectedDentist);

    var inputData = {
      firstname: this.firstname,
      lastname: this.lastname,
      username: this.username,
      password: this.password, 
      email:  this.email, 
      roleId:this.selectedRole,
      whomId:0 // default value
    }

    if (this.selectedPatient !== undefined) {
      inputData.whomId = this.selectedPatient;
    }else if (this.selectedDentist !== undefined) {
      inputData.whomId = this.selectedDentist;
    } 

    console.log(inputData);
    this.userService.getUser(this.username).subscribe({
      next: (res:any) =>{ }
    });

    this.userService.saveUser(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","User Successfuly Saved. Add another one.",'success');
        this.clearFields();
        this.isLoading = false;
      },
      error:(err:any)=>{
        this.errors = err.error.errors; 
        console.log(err.error.errors, 'errors');
        Swal.fire('Error','Error in Saving User','error');
        this.isLoading = false;
      }
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

  loadingPatientData(){
    this.patientService.getPatientsExcludePagination().subscribe(
      (data:any)=>{ 
        this.patients = data;
        //console.log(this.surgeris);
      },
      (error)=>{
        console.log(error);
        Swal.fire('Error !!',"Error in loading data from server",'error');
      }
    );
  }

  // clear form field value
  clearFields() {
    this.firstname  = '';
    this.lastname   = '';
    this.username   = '';
    this.password   = '';
  }

  //data validation
  public formValidated(){
    if(this.firstname == ''||this.firstname == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    } 
    if(this.username == ''||this.username == null){
      return false;
    }
    if(this.password == ''||this.password == null){
      return false;
    }

    this.userService.getUser(this.username).subscribe(data=>{
      if(data != null){
        console.log(data);
        alert("Username already exist");
        return false;
      } 
    });
    // if(this.roleId == null){
    //   return false;
    // }
    return true;
  }

}
