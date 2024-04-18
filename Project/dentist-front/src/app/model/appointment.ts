import { Time } from "@angular/common";
import { Dentist } from "./dentist";
import { Patient } from "./patient";

export class Appointment {

    appointmentId:number;
    appointmentDate:Date;
    appointmentTime:Time;
    patientId:number;
    dentistId:number;
    requestId:number;
    branchId:number;
    dentistDTO:Dentist;
    patientDTO:Patient;
}
