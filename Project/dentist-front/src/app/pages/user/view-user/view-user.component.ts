import { Component } from '@angular/core';
import Swal from 'sweetalert2';
import { User } from '../../../model/user';
import { UserService } from '../../../service/user.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrl: './view-user.component.css'
})
export class ViewUserComponent {

  private page:number = 0;
  private pages:Array<number>
  private userdata:User[];

  isLoading:Boolean = false;

  constructor(private userService:UserService){}

  ngOnInit(): void {
    this.getUserList();
  }

  setPage( i, event:any){
    event.preventDefault();
    this.page = i;
    this.getUserList();
  }

  getUserList(){
    this.isLoading = true;
    this.userService.getAllUserList().subscribe(data=>{
      this.userdata = data;
      this.pages=new Array(5);
      this.isLoading = false;
    });
  }

  deleteUser(event:any, uid){
    if(confirm("Are you sure want to Delete this data ?")){
      event.target.innerText = "Deleteting ...";
      this.userService.deleteUserById(uid).subscribe((data:any)=>{
          Swal.fire("Success !!","User Deleted.",'success')
          .then((e)=>{ 
            this.getUserList(); 
          });
      });
    } 
  }


}
