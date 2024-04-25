import { Component } from '@angular/core';
import { DentistService } from '../../../service/dentist.service';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-edit-dentist',
  templateUrl: './edit-dentist.component.html',
  styleUrl: './edit-dentist.component.css'
})
export class EditDentistComponent {

  dentist:any;
  dentistId!:any;
   
  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private dentistService:DentistService,
    private _route:ActivatedRoute,
    private _router:Router){}

  ngOnInit(): void {
    this.dentistId = this._route.snapshot.paramMap.get('dentid'); 
    this.isLoading = true;
    this.dentistService.getDentistById(this.dentistId).subscribe(data=>{
      console.log(data);
      this.dentist = data;
      this.isLoading = false;
    });
   
  }

  updateDentist(){

    if(!this.formValidated()){
      return;
    }

    this.isLoading = true;
    this.loadingTitle = 'Updating';
    
    var inputData = {
      dentistId:this.dentist.dentistId,
      firstName: this.dentist.firstName,
      lastName: this.dentist.lastName,
      phoneNumber: this.dentist.phoneNumber,
      email:  this.dentist.email,
      specialization: this.dentist.specialization 
      }

    //console.log(inputData,'response');
    
    this.dentistService.updateDentist(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Dentist Register Updated.",'success')
        .then((e)=>{
          this.isLoading = false;
          this._router.navigate(['/view-dentist']);
        });
      },
      error:(err:any)=>{ 
        console.log(err, 'errors');
        Swal.fire('Error','Error in updating dentist registration','error');
        this.isLoading = false;
      }
    });
  }

  public formValidated(){
    if(this.dentist.firstName == ''||this.dentist.firstName == null){ 
      return false;
    }
    if(this.dentist.phoneNumber == ''||this.dentist.phoneNumber == null){
      return false;
    }
    return true;
  }

}
