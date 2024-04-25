import { Component, OnInit } from '@angular/core';
import { Address } from '../../../model/address';
import { SurgeryService } from '../../../service/surgery.service';
import Swal from 'sweetalert2';
import { ActivatedRoute, Router } from '@angular/router';
import { faL } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-edit-surgery',
  templateUrl: './edit-surgery.component.html',
  styleUrl: './edit-surgery.component.css'
})
export class EditSurgeryComponent implements OnInit {

  surgery:any;
  branchId!:any;
  addressId!:any;

  // branchname!: String;
  // telephoneno!: String;
  // street1!: String;
  // street2!: String;
  // state!: String;
  // city!: String;
  // zipcode!: number; 
  // address!: Address; 

  errors:any = [];

  isLoading:Boolean = false;
  loadingTitle:String = '';
  constructor(private surgeryService:SurgeryService,
    private _route:ActivatedRoute,
    private _router:Router){}

  ngOnInit(): void {
    this.branchId = this._route.snapshot.paramMap.get('brid');
    this.addressId = this._route.snapshot.paramMap.get('addid');
    this.isLoading = true;
    this.surgeryService.getBranchDataById(this.branchId).subscribe(data=>{
      console.log(data);
      this.surgery = data;
      this.isLoading = false;
    });
   
  }

  updateLocation(){

    if(!this.formValidated()){
      return;
    }

    this.isLoading = true;
    this.loadingTitle = 'Updating';
    
    var inputData = {
      branchId: this.surgery.branchId,
      branchName: this.surgery.branchName,
      telephoneNo: this.surgery.telephoneNo,
      companyId: 1,
      address: {
          addressId:this.surgery.address.addressId,
          street1: this.surgery.address.street1,
          street2: this.surgery.address.street2,
          state: this.surgery.address.state,
          city: this.surgery.address.city,
          zipcode: this.surgery.address.zipcode,
        }
      }

    //console.log(inputData,'response');
    
    this.surgeryService.updateLocation(inputData).subscribe({
      next: (res:any) =>{ 
        Swal.fire("Success !!","Location Updated.",'success')
        .then((e)=>{
          this.isLoading = false;
          this._router.navigate(['/view-surgery']);
        });
      },
      error:(err:any)=>{ 
        console.log(err, 'errors');
        Swal.fire('Error','Error in updating location','error');
        this.isLoading = false;
      }
    });
  }

  public formValidated(){
    if(this.surgery.branchName == ''||this.surgery.branchName == null){
      // this._snack.open('Content is required','',{
      //  duration:3000,
      // });
      return false;
    }
    if(this.surgery.telephoneNo == ''||this.surgery.telephoneNo == null){
      return false;
    }
    if(this.surgery.address.street1 == ''||this.surgery.address.street1 == null){
      return false;
    }
    if(this.surgery.address.state == ''||this.surgery.address.state == null){
      return false;
    }
    if(this.surgery.address.city == ''||this.surgery.address.city == null){
      return false;
    }
    if(this.surgery.address.zipcode == null){
      return false;
    }
    return true;
  }
 
}
