import { Component, OnInit } from '@angular/core';
import { SurgeryService } from '../../../service/surgery.service';
import { Surgery } from '../../../model/surgery';
import { faL } from '@fortawesome/free-solid-svg-icons';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-surgery',
  templateUrl: './view-surgery.component.html',
  styleUrl: './view-surgery.component.css'
})
export class ViewSurgeryComponent implements OnInit {

  surgerydata:Surgery[]; 
  isLoading:Boolean = false;
 
  constructor(private surgeryService:SurgeryService){}
  
  ngOnInit(): void {
    this.getSurgeryList();
  }

  getSurgeryList(){
    this.isLoading = true;
    this.surgeryService.getAllBranch().subscribe(data=>{
      this.surgerydata = data;
      this.isLoading = false;
    });
  }

  deleteSurgery(event:any, branchid){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.surgeryService.deleteBranchDataById(branchid).subscribe((data:any)=>{
          Swal.fire("Success !!","Location Deleted.",'success')
          .then((e)=>{ 
            this.getSurgeryList(); 
          });
      });
    } 
  }

}
