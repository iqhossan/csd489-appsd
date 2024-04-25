import { Address } from "./address";

export class Patient {
    patientId:number;
    firstName:string;
    lastName:string;
    phoneNo:string;
    email:string;
    dues:number = 0.0; 
    addressId:number;
    addreee:Address;
}
